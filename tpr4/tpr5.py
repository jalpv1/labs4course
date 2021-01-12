#%%
import numpy as np
import math
alternatives =[[5,  3 , 1 , 5,  9 , 1 , 7,  9,  2 , 9 , 4, 1] ,
[ 2,  2,  5 , 4 , 4,  6,  1 , 10,  8,  6 , 9 , 8] ,
 [5 , 9 , 2 , 9  ,1 , 5, 7 , 2 , 6 , 7,  6,  6],
 [7 , 6 ,6  ,2  ,2,  9 , 3 , 8 , 5  ,10 , 3,  6],
 [6 , 2  ,1 , 8 , 1  ,10 , 6 ,9 , 8  ,6 , 7,  1],
 [9  ,5  ,5 , 5 , 10 , 6  ,3  ,9  ,2 , 7,  1 , 7],
 [8  ,2  ,6 , 8 , 10  ,1 , 8 , 2  ,7,  2  ,4,  8] ,
 [10 , 10,  4 ,4 , 8 , 9,  4 , 6 , 7,  2, 7 , 8],
 [9 , 10  ,3 , 5,  10  ,6  ,3,  6 , 4,  2 , 3,  5] ,
 [1 , 9  ,7 , 7 , 6 , 1 , 5 , 8 , 1  ,6 ,6  ,2 ],
 [4 , 10  ,9 , 9,  2 , 1 ,7 , 7  ,5 , 8,  3,  5 ],
 [4  ,6, 9  ,4  ,9,  8,  3 , 10,  5, 10 , 10 , 3 ],
 [10,  6, 10,  6 , 6 , 7,  3 , 7 , 3  ,1,  8 , 2],
 [7 , 3 , 4 , 1 , 5 , 10 , 8 , 8,  7,  4 , 6,  10 ],
 [2 , 5 , 4 , 9 , 1  ,5  ,9  ,6,  7 , 10 , 1,  1 ]]
weights = [ 9, 9, 4, 1, 2, 3, 1, 8, 6, 3, 1, 6]

c = 0.754
d = 0.481

def normalize_weights(weights):
    sum_weights = sum(weights)
    normalized_weights = []
    for i in range(0,len(weights)):
        normalized_weights.append(weights[i]/sum_weights)
    return normalized_weights

# множення матриці на матрицю ваг критеріїв
def weighted_estimates(r_matrix, weights):
    norm_weights = normalize_weights(weights)
    for i in range(0, 15):
        for j in range(0, 12):
            r_matrix[i][j] = r_matrix[i][j] * norm_weights[j]
    return r_matrix

# виведення результатів
def output_result(result, opt, method):
    print(method)
    print("Ранжування:")
    for i in result:
        print(i, end=" ")
    print()
    if len(opt)==1:
        print("Найкраща альтернатива:")
    else:
        print("Множина кращих альтернатив:")
    for i in opt:
        print(i, end=" ")
    print()

def normalize_astimates_uniform(alternatives):
    r_matrix = [[0]*12 for i in range(15)]
    a = np.array(alternatives)
    pows = []
    for k in range(0,12):
        column = a[:,k]
        for i in column:
            i = pow(i, 2)
        pows.append(sum(column)) 
    for i in range(0, 15):
        for j in range(0, 12):
            r_matrix[i][j] = alternatives[i][j]/(math.sqrt(pows[j]))   
    return r_matrix

# нормалізація при k1-k7 підлягають максимізації, а критерії k8-k12 – мінімізації
def normalize_astimates_kplus_kminus(alt):
    alternatives = np.array(alt)
    kplus_criteria = np.empty((15,7))
    kminus_criteria = np.empty((15,5))
    r_matrix = np.empty((15,12))
    for i in range (0,15):
        for j in range (0,7):
            kplus_criteria[i][j] = alternatives[i][j]
    for i in range (0,15):
        for j in range (7,12):
            kminus_criteria[i][j-7] = alternatives[i][j]
    # нормалізація критеріїв, що підлягають максимізації
    for i in range(0, 15):
        for j in range(0, 7):
            min_kplus = min(kplus_criteria[:,j])
            max_kplus = max(kplus_criteria[:,j])
            kplus_criteria[i][j] = (kplus_criteria[i][j]-min_kplus)/(max_kplus-min_kplus)
    # нормалізація критеріїв, що підлягають мінімізації
    for i in range(0, 15):
        for j in range(0, 5):
            min_kminus = max(kminus_criteria[:,j])
            max_kminus = min(kminus_criteria[:,j])
            kminus_criteria[i][j] = (min_kminus-kminus_criteria[i][j])/(min_kminus-max_kminus)
    for i in range (0,15):
        for j in range (0,7):
            r_matrix[i][j] = kplus_criteria[i][j]
    for i in range (0,15):
        for j in range (7,12):
            r_matrix[i][j] = kminus_criteria[i][j-7]
    return r_matrix

# розраунок відстаней до утопічної та антиутопічної точки
def calculate_d_pis_nis(row_i, max_j, min_j):
    r_pis = []
    r_nis = []
    for i in range(0, len(row_i)):
        r_pis.append(pow((row_i[i]-max_j[i]), 2))
        r_nis.append(pow((row_i[i]-min_j[i]), 2))
    d_pis = math.sqrt(sum(r_pis))
    d_nis = math.sqrt(sum(r_nis))
    return d_pis, d_nis

# Встановлення наближеності кожної альтернативи до позитивної ідеальної точки
def calculate_c(d_pis, d_nis):
    c = []
    for i in range(0, len(d_pis)):
        c.append(d_nis[i]/(d_pis[i] + d_nis[i]))
    return c

# TOPSIS
def topsis(alternatives, weights, task):
    if task == "a":
        # нормалізація за загальною формулою
        norm_a = normalize_astimates_uniform(alternatives)
    else:
        # нормалізація за формулами для критеріїв прибутку та критеріїв витрат
        norm_a = normalize_astimates_kplus_kminus(alternatives)
    # Обчислення зважених нормалізованих оцінок альтернатив
    weighted_a = np.array(weighted_estimates(norm_a, weights))
    max_j = []
    min_j = []
    for j in range(0, 12):
        # визначення утопічної та антиутопічної точки
        max_j.append(max(weighted_a[:,j]))
        min_j.append(min(weighted_a[:,j]))
    d_pis = []
    d_nis = []
    for i in range(0, 15):
        # відстані до утопічної та антиутопічної точок
        d_p, d_n = calculate_d_pis_nis(weighted_a[i], max_j, min_j)
        d_pis.append(d_p)
        d_nis.append(d_n)
    # розрахунок наближеності до утопічної та антиутопічної точки
    c = calculate_c(d_pis, d_nis)
    indexes = np.argsort(c)
    for i in range(0,len(indexes)):
        indexes[i]+=1
    result = indexes[::-1]
    opt = []
    opt.append(result[0])
    return result, opt

def print_topsis():
    resultA, optA = topsis(alternatives, weights, "a")
    resultB, optB = topsis(alternatives, weights, "b")
    output_result(resultA, optA, "TOPSIS - всі критерії потрібно максимізувати")
    print("------------------------------------------------------------")
    output_result(resultB, optB, "TOPSIS - k1-k7 підлягають максимізації, а k8-k12 мінімізації")

print_topsis()


def calculate_f_max_min_values(alt):
    alternatives = np.array(alt)
    max_f = []
    min_f = []
    for j in range (0,12):
        max_f.append(max(alternatives[:,j]))
        min_f.append(min(alternatives[:,j]))
    return max_f, min_f

# нормалізація критеріїв
def create_vikor_matrix(alternatives, max_f, min_f):
    vikor_matrix = np.empty((15,12))
    for i in range(0,15):
        for j in range(0,12):
            vikor_matrix[i][j] = (max_f[j]-alternatives[i][j]) / (max_f[j]-min_f[j])                
    return vikor_matrix

# обчислення середнього інтервалу покращення альтернативи
def calculate_sk(weighted_vikor_matrix):
    sk = []
    for i in weighted_vikor_matrix:
        sk.append(sum(i))
    max_sk = max(sk)
    min_sk = min(sk)
    return sk, max_sk, min_sk

# обчислення максимального інтервалу покращення альтернативи
def calculate_rk(weighted_vikor_matrix):
    rk = []
    for i in weighted_vikor_matrix:
        rk.append(max(i))
    max_rk = max(rk)
    min_rk = min(rk)
    return rk, max_rk, min_rk

# перевірка виконання умов С1 та С2
def check_c1_c2(q, min_sk, min_rk, q_i, sk_i, rk_i):
    check_c1 = False
    check_c2 = False
    if q[1]-q[0]>=1/14:
        check_c1 = True
    if q_i[0] == sk_i[0] or q_i[0] == rk_i[0]:
        check_c2 = True
    return check_c1, check_c2

def vikor(alternatives, v):
    # множини бажаних та найгірших значень
    max_f, min_f = calculate_f_max_min_values(alternatives)
    # нормалізація та врахування ваг критеріїв
    vikor_matrix = create_vikor_matrix(alternatives, max_f, min_f)
    weighted_vikor_matrix = weighted_estimates(vikor_matrix, weights)
    # середні інтервали покращення альтернатив
    sk, max_sk, min_sk = calculate_sk(weighted_vikor_matrix)
    # максимальні інтервали покращення альтернатив
    rk, max_rk, min_rk = calculate_rk(weighted_vikor_matrix)
    q = []
    for i in range(0,15):
        # Обчислення значень Qk , k=1,2...,n для кожної альтернативи 
        q.append(v*(sk[i]-min_sk)/(max_sk-min_sk)+(1-v)*(rk[i]-min_rk)/(max_rk-min_rk))
    q_indexes = np.argsort(q)
    sk_indexes = np.argsort(sk)
    rk_indexes = np.argsort(rk)    
    print("Ранжування Q:")
    for i in q_indexes:
        print(i+1, end=" ")
    print()
    print("Ранжування S")
    for i in sk_indexes:
        print(i+1, end=" ")
    print()
    print("Ранжування R")
    for i in rk_indexes:
        print(i+1, end=" ")
    print()
    q_sorted = sorted(q)
    # перевірка виконання умов С1 та С2
    check_c1, check_c2 = check_c1_c2(q, min_sk, min_rk, q_indexes, sk_indexes, rk_indexes)
    opt = []
    opt_values = []
    # виведення результатів, якщо умови вконуються
    if check_c1==True and check_c2==True:
        opt.append(q_indexes[0]+1)
        opt_values.append(q[0])
    # створення множини кращих альтернатив, якщо С1 не виконується
    elif check_c1==False and check_c2==True:
        print ("С1 не виконуються")
        print()
        opt.append(q_indexes[0]+1)
        opt_values.append(q[0])
        for i in range(1,len(q)):           
            if q[i]-q[i-1] < 1/14:
                opt.append(q_indexes[i]+1)
                opt_values.append(q[i])
            else:
                break
    # створення множини кращих альтернатив, якщо С2 не виконується
    elif check_c1==True and check_c2==False:
        print ("С2 не виконуються")
        print()
        opt.append(q_indexes[0]+1)
        opt_values.append(q[0])
        opt.append(q_indexes[1]+1)
        opt_values.append(q[1])
    else:
        print ("С1 та С2 не виконуються")
        print()
    for i in range(0,len(q_indexes)):
        q_indexes[i]+=1
    return q_indexes, opt, opt_values

def print_vikor():
    result, opt, opt_values = vikor(alternatives, 0.5)
    output_result(result, opt, "VIKOR - v=0.5")
    print("Значення Q кращих альтернатив:")
    for i in opt_values:
        print(i, end=' ')
    print()
    print("ДОСЛІДЖЕННЯ")
    print("------------------------------------------------------------")
    v_values = [0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1]
    for v in v_values:
        result, opt, opt_values = vikor(alternatives, v)
        output_result(result, opt, "VIKOR - v={}".format(v))
        print("Значення Q кращих альтернатив:")
        for i in opt_values:
            print(i, end=' ')
        print()
        print("------------------------------------------------------------")
        
print_vikor()