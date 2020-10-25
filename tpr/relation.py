import numpy as np

task_array = [[1, 9, 10, 8, 7, 7, 4, 4, 9, 6, 5, 2],
              [6, 9, 10, 8, 7, 9, 4, 4, 9, 7, 6, 10],
              [1, 7, 4, 8, 6, 7, 4, 4, 2, 4, 1, 2],
              [1, 4, 4, 7, 3, 7, 2, 4, 2, 4, 1, 2],
              [1, 4, 4, 2, 3, 1, 2, 3, 2, 4, 1, 2],
              [5, 9, 4, 2, 3, 8, 2, 5, 9, 9, 5, 10],
              [1, 4, 4, 2, 3, 3, 2, 4, 2, 4, 1, 2],
              [1, 3, 2, 2, 3, 3, 2, 4, 2, 4, 1, 2],
              [10, 9, 10, 8, 7, 9, 4, 4, 9, 10, 9, 10],
              [10, 9, 10, 8, 7, 9, 5, 4, 9, 10, 9, 10],
              [1, 2, 2, 2, 3, 3, 2, 4, 2, 4, 1, 2],
              [1, 8, 6, 6, 3, 9, 7, 7, 3, 4, 1, 8],
              [1, 1, 3, 6, 3, 7, 2, 3, 2, 4, 1, 2],
              [1, 1, 3, 2, 3, 3, 1, 1, 2, 4, 1, 2],
              [1, 1, 2, 2, 3, 3, 1, 1, 2, 3, 1, 2],
              [3, 3, 8, 5, 10, 3, 3, 3, 7, 8, 1, 8],
              [10, 7, 10, 9, 10, 7, 8, 4, 7, 8, 3, 8],
              [10, 7, 10, 9, 10, 7, 8, 4, 10, 9, 3, 9],
              [4, 6, 2, 2, 3, 7, 2, 2, 9, 3, 3, 8],
              [1, 4, 2, 2, 2, 7, 1, 1, 5, 3, 3, 8],
              ]


def compare_alternatives(a1, a2):
    result = np.arange(len(a1))
    for i in range(0, len(a1)):

        if a1[i] < a2[i]:
            result[i] = -1
            continue

        elif a1[i] > a2[i]:
            result[i] = 1
            continue

        elif a1[i] == a2[i]:
            result[i] = 0

    return result


def sigma(matrix):
    alternatives = np.array(matrix)
    result = [[0] * 20 for i in range(20)]
    for i in range(0, 20):
        for j in range(0, 20):
            result[i][j] = compare_alternatives(alternatives[i], alternatives[j])
    return result


s_v = sigma(task_array)
s_v


# симетрична частина
def I(r):
    return (r == r.T) * r


# асиметрична частина
def P(r):
    return r - I(r)


# відношення непорівнюваності
def N(r):
    return (r == r.T) - I(r)


# найбільші по Р
def domMaxP(m):
    matrix = np.array(m)
    maxP = []
    for i in range(0, len(matrix)):
        if matrix[i][i] == 0 and matrix[i].sum() == len(matrix) - 1:
            maxP.append(i + 1)
    return maxP

def domMaxR(m):
    matrix = np.array(m)
    maxR = []
    strongMax = []
    for i in range(0, len(matrix)):
        if matrix[i].sum() == len(matrix):
            maxR.append(i + 1)
            if matrix[:, i].sum() == 1:
                strongMax.append(i + 1)
    return maxR, strongMax


# максимальні по Р
def blockMaxP(m):
    matrix = np.array(m)
    maxP = []
    for i in range(0, len(matrix)):
        if matrix[:, i].sum() == 0:
            maxP.append(i + 1)
    return maxP


def blockMaxR(m):
    matrix = np.array(m)
    sym = I(matrix)
    maxR = []
    strong_max = []
    for i in range(0, len(matrix)):
        if np.any(np.array_equal(matrix[:, i], sym[:, i]) == False) == False:
            maxR.append(i + 1)
            if matrix[:, i].sum() == 1 and matrix[i][i] == 1:
                strong_max.append(i + 1)
    return maxR, strong_max


def symetricCheck(matrix):
    sym = I(np.array(matrix))
    indicator = False
    for i in range(0, len(sym)):
        if sym[i].sum() > 0:
            indicator = True
            break
    return indicator


def printMaxElement(matrix):
    if symetricCheck(matrix):
        m, sm = domMaxR(matrix)
        if len(m) > 0:
            print("Max  R - {}".format(m))
            print("strong max  R- {}".format(sm))
        else:
            m, sm = blockMaxR(matrix)
            print("Max  R - {}".format(m))
            print("strong max  R- {}".format(sm))
    else:
        if len(domMaxP(matrix)) > 0:
            print("the biggest Р - {}".format(domMaxP(matrix)))
        else:
            print(" max  Р -  {}".format(blockMaxP(matrix)))  # симетрична частина


def paretoCheckV(vector):
    check = np.array(vector)
    indicator = True
    for i in range(0, len(check)):
        if check[i] >= 0:
            pass
        else:
            indicator = False
    if indicator:
        return 1
    else:
        indicator = True
        for i in range(0, len(check)):
            if check[i] <= 0:
                pass
            else:
                indicator = False
        if indicator:
            return 2
        else:
            return 3


def pareto(sigmaVectors):
    vectorsMatrix = np.array(sigmaVectors)
    resultMatrix = [[0] * 20 for i in range(20)]
    for i in range(0, 20):
        resultMatrix[i][i] = 1
    for i in range(0, len(vectorsMatrix)):
        for j in range(i + 1, len(vectorsMatrix)):
            flag = paretoCheckV(vectorsMatrix[i][j])
            if flag == 1:
                resultMatrix[i][j] = 1
                resultMatrix[j][i] = 0
                continue
            elif flag == 2:
                resultMatrix[i][j] = 0
                resultMatrix[j][i] = 1
                continue
            elif flag == 3:
                resultMatrix[i][j] = 0
                resultMatrix[j][i] = 0
    f = open("pareto.txt", "w")
    f.write(" 1" + '\n')
    for i in range(0, len(resultMatrix)):
        for j in range(0, len(resultMatrix)):
            f.write(" {} ".format(resultMatrix[i][j]))
        f.write('\n')
    f.close()
    return resultMatrix


printMaxElement(pareto(s_v))
pareto(s_v)


def majority(sigmaVectors):
    vectorsMatrix = np.array(sigmaVectors)
    resultMatrix = [[0] * 20 for i in range(20)]
    for i in range(0, len(vectorsMatrix)):
        for j in range(i, len(vectorsMatrix)):
            if vectorsMatrix[i][j].sum() > 0:
                resultMatrix[i][j] = 1
                resultMatrix[j][i] = 0
                continue
            # якщо сума елементів сигма-вектора < 0 - пара альтернатив не належить відношенню, симетрична належить
            elif vectorsMatrix[i][j].sum() < 0:
                resultMatrix[i][j] = 0
                resultMatrix[j][i] = 1
                continue
            # якщо сума елементів сигма-вектора = 0 - пара альтернатив та симетричне ій не належать відношенню
            elif vectorsMatrix[i][j].sum() == 0:
                resultMatrix[i][j] = 0
                resultMatrix[j][i] = 0
    f = open("majority.txt", "w")
    f.write(" 2" + '\n')
    for i in range(0, len(resultMatrix)):
        for j in range(0, len(resultMatrix)):
            f.write(" {} ".format(resultMatrix[i][j]))
        f.write('\n')
    f.close()
    return resultMatrix


printMaxElement(majority(s_v))
majority(s_v)


def sortStrongOrder(a):
    ordered = np.arange(len(a))
    # k4>k11>k3>k9>k5>k1>k7>k2>k12>k10>k6>k8
    order = np.array([3, 10, 2, 8, 4, 0, 6, 1, 11, 9, 5, 7])
    for i in range(0, len(a)):
        ordered[i] = a[order[i]]
    return ordered


def sortSigmaVectors(sigmaVectors):
    vectorVatrix = np.array(sigmaVectors)
    sortedMatrix = [[0] * 20 for i in range(20)]
    for i in range(0, len(vectorVatrix)):
        for j in range(0, len(vectorVatrix)):
            sortedMatrix[i][j] = sortStrongOrder(vectorVatrix[i][j])
    return sortedMatrix


def lexicographic(matrix):
    sorted_vectors = np.array(matrix)
    result_matrix = [[0] * 20 for i in range(20)]
    for i in range(0, len(result_matrix)):
        result_matrix[i][i] = 0
    for i in range(0, len(sorted_vectors)):
        for j in range(i + 1, len(sorted_vectors)):
            a = sorted_vectors[i][j]
            for k in range(0, len(a)):
                if a[k] == 1:
                    result_matrix[i][j] = 1
                    result_matrix[j][i] = 0
                    break
                elif a[k] == -1:
                    result_matrix[i][j] = 0
                    result_matrix[j][i] = 1
                    break
                else:
                    continue
    f = open("lexicographic.txt", "w")
    f.write(" 3" + '\n')
    for i in range(0, len(result_matrix)):
        for j in range(0, len(result_matrix)):
            f.write(" {} ".format(result_matrix[i][j]))
        f.write('\n')
    f.close()
    return result_matrix


printMaxElement(lexicographic(sortSigmaVectors(s_v)))
lexicographic(sortSigmaVectors(s_v))


#
def setVectors(a):
    #{k1,k3,k6} < {k9,k10} < {k2,k4,k5,k7,k8,k11,k12}

    first = np.array([ a[0], 0, 0,  a[3], 0,  a[5], 0, 0, 0, 0, 0, 0])
    second = np.array([0, 0, 0, 0, 0, 0, 0, 0,  a[8],  a[9], 0, 0])
    third = np.array([0,0,  a[1],  a[4],  a[5], 0, a[6],  a[7], 0, 0,  a[10],  a[11]])
    return first, second, third


def divVectorsIntoClasses(sigma_vectors):
    vectors_matrix = np.array(sigma_vectors)
    class1 = [[0] * 20 for i in range(20)]
    class2 = [[0] * 20 for i in range(20)]
    class3 = [[0] * 20 for i in range(20)]
    for i in range(0, 20):
        for j in range(0, 20):
            a = vectors_matrix[i][j]
            class1[i][j], class2[i][j], class3[i][j] = setVectors(a)

    return class1, class2, class3


def berezovskiy(sigma_vectors):
    iter1_p = [[0] * 20 for i in range(20)]
    iter1_i = [[0] * 20 for i in range(20)]
    iter1_n = [[0] * 20 for i in range(20)]
    result_matrix = [[0] * 20 for i in range(20)]
    c1, c2, c3 = divVectorsIntoClasses(s_v)
    pareto1 = np.array(pareto(c1))
    pareto2 = np.array(pareto(c2))
    pareto3 = np.array(pareto(c3))

    i1 = I(pareto1)
    p1 = P(pareto1)
    n1 = N(pareto1)
    # I02, P02, N02
    i2 = I(pareto2)
    p2 = P(pareto2)
    n2 = N(pareto2)
    # I03, P03, N03
    i3 = I(pareto3)
    p3 = P(pareto3)
    n3 = N(pareto3)

    # Формуємо Pb1, Ib1, Nb1
    for i in range(0, len(result_matrix)):
        for j in range(0, len(result_matrix)):
            if p2[i][j] == 1 and p2[i][j] == p1[i][j]:
                iter1_p[i][j] = 1
            if p2[i][j] == 1 and p2[i][j] == n1[i][j]:
                iter1_p[i][j] = 1
            if i2[i][j] == 1 and i2[i][j] == p1[i][j]:
                iter1_p[i][j] = 1
            if p2[i][j] == 1 and p2[i][j] == i1[i][j]:
                iter1_p[i][j] = 1
            if i2[i][j] == 1 and i2[i][j] == i1[i][j]:
                iter1_i[i][j] = 1
    for i in range(0, len(result_matrix)):
        for j in range(0, len(result_matrix)):
            if iter1_p[i][j] == 0 and iter1_i[i][j] == 0:
                iter1_n[i][j] == 1
    # Формуємо Pb2 (результат) порівнюючи I03, P03, N03 з Pb1, Ib1, Nb1
    for i in range(0, len(result_matrix)):
        for j in range(0, len(result_matrix)):
            if p3[i][j] == 1 and p3[i][j] == iter1_p[i][j]:
                result_matrix[i][j] = 1
            if p3[i][j] == 1 and p3[i][j] == iter1_n[i][j]:
                result_matrix[i][j] = 1
            if i3[i][j] == 1 and i3[i][j] == iter1_p[i][j]:
                result_matrix[i][j] = 1
            if p3[i][j] == 1 and p3[i][j] == iter1_i[i][j]:
                result_matrix[i][j] = 1
    f = open("berezovskiy.txt", "w")
    f.write(" 4" + '\n')
    for i in range(0, len(result_matrix)):
        for j in range(0, len(result_matrix)):
            f.write(" {} ".format(result_matrix[i][j]))
        f.write('\n')
    f.close()
    return result_matrix


printMaxElement(berezovskiy(s_v))
berezovskiy(s_v)


def psiAlternatives(matrix):
    alternatives = np.array(matrix)
    for i in range(0, 20):
        a = alternatives[i]
        a[::-1].sort()
        alternatives[i] = a
    return alternatives


def podynovskiy(matrix):
    # формування множини функції псі від альтернатив(сортування за спаданням)
    psi_a = psiAlternatives(matrix)
    # формування сигма-векторів побудованих для псі(аі)
    sv = sigma(psi_a)
    # відношення Парето для множини псі(аі)
    resultMatrix = pareto(sv)
    f = open("podynovskiy.txt", "w")
    f.write(" 5" + '\n')
    for i in range(0, len(resultMatrix)):
        for j in range(0, len(resultMatrix)):
            f.write(" {} ".format(resultMatrix[i][j]))
        f.write('\n')
    f.close()
    return resultMatrix


printMaxElement(podynovskiy(task_array))
podynovskiy(task_array)
data = data2 = data3 = data4 = data5 = ""

# Reading data from file1
with open('berezovskiy.txt') as fp:
    data = fp.read()

# Reading data from file2
with open('lexicographic.txt') as fp:
    data2 = fp.read()

with open('majority.txt') as fp:
    data3 = fp.read()

# Reading data from file2
with open('podynovskiy.txt') as fp:
    data4 = fp.read()
with open('pareto.txt') as fp:
    data5 = fp.read()

# Merging 2 files
# To add the data of file2
# from next line
data += "\n"
data = data + data2 + data3 + data4 + data5

with open('result.txt', 'w') as fp:
    fp.write(data)
