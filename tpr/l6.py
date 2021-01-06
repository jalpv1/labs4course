import numpy as np

expert1 = [[0.2, 0.2, 0.6, 0.4, 0.7, 0.2],
           [0.7, 0.9, 0.1, 0.9, 0.9, 0.9],
           [0.0, 0.4, 0.6, 0.1, 0.6, 0.9],
           [0.3, 0.7, 1.0, 1.0, 0.8, 0.7],
           [0.1, 0.8, 0.2, 0.1, 0.9, 0.2],
           [0.9, 0.1, 0.1, 0.3, 0.2, 0.6]]

expert2 = [[0.1, 0.8, 0.7, 0.2, 0.8, 0.6],
           [0.3, 0.4, 0.0, 0.9, 1.0, 0.3],
           [0.4, 0.7, 0.7, 0.1, 1.0, 0.8],
           [0.6, 0.1, 0.5, 0.4, 0.5, 0.8],
           [0.5, 0.4, 0.8, 0.7, 0.7, 0.7],
           [0.4, 0.3, 0.7, 0.4, 0.1, 0.3]]

expert3 = [[0.4, 0.9, 0.9, 0.3, 0.3, 0.6],
           [0.9, 0.5, 0.2, 1.0, 0.7, 0.3],
           [0.1, 0.4, 0.9, 0.6, 0.3, 0.9],
           [0.8, 0.7, 0.1, 0.8, 0.4, 0.3],
           [0.9, 0.3, 0.3, 0.8, 0.2, 0.0],
           [0.7, 0.5, 0.5, 0.1, 0.1, 0.1]]

expert4 = [[0.3, 0.6, 0.4, 0.3, 0.4, 0.6],
           [0.1, 0.8, 0.8, 0.1, 0.9, 0.1],
           [0.3, 0.6, 0.8, 0.6, 0.1, 0.0],
           [0.5, 0.4, 0.6, 0.5, 0.7, 0.7],
           [0.6, 0.5, 0.9, 0.1, 0.4, 0.0],
           [0.2, 0.2, 0.3, 0.4, 0.4, 0.5]]

expert5 = [[0.1, 1.0, 0.4, 0.0, 0.4, 0.3],
           [0.5, 0.9, 0.2, 0.2, 0.6, 0.9],
           [0.2, 0.9, 0.3, 0.4, 0.3, 0.9],
           [0.5, 1.0, 0.8, 0.3, 0.4, 0.4],
           [0.4, 1.0, 0.8, 0.5, 0.0, 0.6],
           [0.7, 0.9, 0.5, 0.2, 0.8, 0.5]]

weights = [0.2, 0.42, 0.11, 0.07, 0.2]


# виведення результатів завдання 1
def print_task1(r, s):
    rs_association = association(r, s)
    rs_intersection = intersection(r, s)
    r_complement = complement(r)
    s_complement = complement(s)
    rs_composition = composition(r, s)
    r_alpha_level_05 = alpha_level(r, 0.5)
    r_alpha_level_09 = alpha_level(r, 0.9)
    r_strict = strict(r)
    for i in range(len(r_strict)):
        for j in range(len(r_strict)):
            r_strict[i][j] = round(r_strict[i][j])
    r_indifference = indifference(r)
    r_quasi_equivalence = quasi_equivalence(r)
    print("Об'єднання R1 та R2:\n")
    for i in rs_association:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Перетин R1 та R2:\n")
    for i in rs_intersection:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Доповнення R1:\n")
    for i in r_complement:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Доповнення R2:\n")
    for i in s_complement:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Композиція R1 та R2:\n")
    for i in rs_composition:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Альфа-рівень 0.5 відношення R1:\n")
    for i in r_alpha_level_05:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Альфа-рівень 0.9 відношення R1:\n")
    for i in r_alpha_level_09:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Відношення строгої переваги для R1:\n")
    for i in r_strict:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Відношення байдужості для R1:\n")
    for i in r_indifference:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Відношення квазіеквівалентності для R1:\n")
    for i in r_quasi_equivalence:
        for j in i:
            print(j, end=' ')
        print()


def print_properties(r1, r2):
    r1_strong_ref, r1_slight_ref = reflexivity(r1)
    r2_strong_ref, r2_slight_ref = reflexivity(r2)
    r1_strong_antiref, r1_slight_antiref = antireflexivity(r1)
    r2_strong_antiref, r2_slight_antiref = antireflexivity(r2)
    r1_sym = symmetry(r1)
    r2_sym = symmetry(r2)
    r1_antisym = antisymmetry(r1)
    r2_antisym = antisymmetry(r2)
    r1_asym = asymmetry(r1)
    r2_asym = asymmetry(r2)
    r1_strong_con, r1_slight_con = connectivity(r1)
    r2_strong_con, r2_slight_con = connectivity(r2)
    r1_trans = transitivity(r1)
    r2_trans = transitivity(r2)
    print("ВЛАСТИВОСТІ")
    print("________________________________________")
    print("Рефлексивність:")
    print()
    print("Відношення R1:")
    if r1_strong_ref:
        print("Має властивість сильної рефлексивності")
    elif r1_slight_ref:
        print("Має властивість слабкої рефлексивності")
    else:
        print("Не має властивості рефлексивності")
    print("Відношення R2:")
    if r2_strong_ref:
        print("Має властивість сильної рефлексивності")
    elif r2_slight_ref:
        print("Має властивість слабкої рефлексивності")
    else:
        print("Не має властивості рефлексивності")
    print("________________________________________")
    print("Антирефлексивність:")
    print()
    print("Відношення R1:")
    if r1_strong_antiref:
        print("Має властивість сильної антирефлексивності")
    elif r1_slight_antiref:
        print("Має властивість слабкої антирефлексивності")
    else:
        print("Не має властивості антирефлексивності")
    print("Відношення R2:")
    if r2_strong_antiref:
        print("Має властивість сильної антирефлексивності")
    elif r2_slight_antiref:
        print("Має властивість слабкої антирефлексивності")
    else:
        print("Не має властивості антирефлексивності")
    print("________________________________________")
    print("Симетричність:")
    print()
    print("Відношення R1:")
    if r1_sym:
        print("Має властивість сильної симетричності")
    else:
        print("Не має властивості симетричності")
    print("Відношення R2:")
    if r2_sym:
        print("Має властивість сильної симетричності")
    else:
        print("Не має властивості симетричності")
    print("________________________________________")
    print("Антисиметричність:")
    print()
    print("Відношення R1:")
    if r1_antisym:
        print("Має властивість сильної антисиметричності")
    else:
        print("Не має властивості антисиметричності")
    print("Відношення R2:")
    if r2_antisym:
        print("Має властивість сильної антисиметричності")
    else:
        print("Не має властивості антисиметричності")
    print("________________________________________")
    print("Асиметричність:")
    print()
    print("Відношення R1:")
    if r1_asym:
        print("Має властивість сильної асиметричності")
    else:
        print("Не має властивості асиметричності")
    print("Відношення R2:")
    if r2_asym:
        print("Має властивість сильної асиметричності")
    else:
        print("Не має властивості асиметричності")
    print("________________________________________")
    print("Зв'язність:")
    print()
    print("Відношення R1:")
    if r1_strong_con:
        print("Має властивість сильної зв'язності")
    elif r1_slight_con:
        print("Має властивість слабкої зв'язності")
    else:
        print("Не має властивості зв'язності")
    print("Відношення R2:")
    if r2_strong_con:
        print("Має властивість сильної зв'язності")
    elif r2_slight_con:
        print("Має властивість слабкої зв'язності")
    else:
        print("Не має властивості рефлексивності")
    print("________________________________________")
    print("Транзитивність:")
    print()
    print("Відношення R1:")
    if r1_trans:
        print("Має властивість сильної транзитивності")
    else:
        print("Не має властивості транзитивності")
    print("Відношення R2:")
    if r2_trans:
        print("Має властивість сильної транзитивності")
    else:
        print("Не має властивості транзитивності")


# %%
# об'єднання
def association(r, s):
    result = [[0.0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(len(r)):
            result[i][j] = max(r[i][j], s[i][j])
    return result


# перетин
def intersection(r, s):
    result = [[0.0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(len(r)):
            result[i][j] = min(r[i][j], s[i][j])
    return result


# доповнення
def complement(r):
    result = [[0.0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(len(r)):
            result[i][j] = round(1 - r[i][j], 1)
    return result


def min_values_vector(row, column):
    result = [0] * len(row)
    for i in range(len(row)):
        result[i] = min(row[i], column[i])
    return result


# композиція
def composition(r, s):
    result = [[0.0] * len(r) for i in range(len(r))]
    r_array = np.array(r)
    s_array = np.array(s)
    for i in range(len(r)):
        for j in range(len(r)):
            result[i][j] = max(min_values_vector(r_array[i], s_array[:, j]))
    return result


# альфа-рівень
def alpha_level(r, alpha):
    result = [[0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(len(r)):
            if r[i][j] >= alpha:
                result[i][j] = 1
            else:
                result[i][j] = 0
    return result


# строга перевага
def strict(r):
    result = [[0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(i + 1, len(r)):
            if r[i][j] > r[j][i]:
                result[i][j] = r[i][j] - r[j][i]
                result[j][i] = 0.0
            else:
                result[j][i] = r[j][i] - r[i][j]
                result[i][j] = 0.0
    return result


# відношення байдужості
def indifference(r):
    result = [[0.0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(len(r)):
            result[i][j] = round(max(1 - max(r[i][j], r[j][i]), min(r[i][j], r[j][i])), 1)
    return result


# відношення квазіеквівалентності
def quasi_equivalence(r):
    result = [[0.0] * len(r) for i in range(len(r))]
    for i in range(len(r)):
        for j in range(len(r)):
            result[i][j] = min(r[i][j], r[j][i])
    return result


print_task1(expert1, expert2)
# %%


# рефлексивність
def reflexivity(r):
    strong = True
    slight = True
    for i in range(len(r)):
        if r[i][i] != 1:
            strong = False
            slight = False
            break
    flag = strong
    if flag:
        for i in range(len(r)):
            for j in range(len(r)):
                if i != j and r[i][j] < 1:
                    continue
                else:
                    strong = False
            for i in range(len(r)):
                for j in range(len(r)):
                    if i != j and r[i][j] <= r[i][i]:
                        continue
                    else:
                        slight = False
    return strong, slight


# антирефлексивність
def antireflexivity(r):
    strong = True
    slight = True
    for i in range(len(r)):
        if r[i][i] != 0:
            strong = False
            slight = False
            break
    flag = strong
    if flag:
        for i in range(len(r)):
            for j in range(len(r)):
                if i != j and r[i][j] > 0:
                    continue
                else:
                    strong = False
            for i in range(len(r)):
                for j in range(len(r)):
                    if i != j and r[i][j] >= r[i][i]:
                        continue
                    else:
                        slight = False
    return strong, slight


# симетричність
def symmetry(r):
    symmetric = True
    for i in range(len(r)):
        for j in range(i, len(r)):
            if r[i][j] != r[j][i]:
                symmetric = False
                break
    return symmetric


# антисиметричність
def antisymmetry(r):
    antisymmetric = True
    diagonal = []
    for i in range(len(r)):
        diagonal.append(r[i][i])
    if sum(diagonal) > 0:
        antisymmetric = False
    else:
        for i in range(len(r)):
            for j in range(i + 1, len(r)):
                if min(r[i][j], r[j][i]) != 0:
                    antisymmetric = False
    return antisymmetric


# асиметричність
def asymmetry(r):
    asymmetric = True
    for i in range(len(r)):
        for j in range(i, len(r)):
            if min(r[i][j], r[j][i]) != 0:
                asymmetric = False
    return asymmetric


# зв'язність
def connectivity(r):
    strong = True
    slight = True
    for i in range(len(r)):
        for j in range(i, len(r)):
            if max(r[i][j], r[j][i]) != 1:
                strong = False
                break
    for i in range(len(r)):
        for j in range(i, len(r)):
            if max(r[i][j], r[j][i]) > 0:
                continue
            else:
                slight = False
                break
    return strong, slight


# транзитивність
def transitivity(r):
    transitive = True
    for i in range(len(r)):
        for j in range(len(r)):
            for k in range(len(r)):
                if r[i][k] >= min(r[i][j], r[j][k]):
                    continue
                else:
                    transitive = False
    return transitive


print_properties(expert1, expert2)


# %%
def one_expert_decision(r):
    # побудова НВ строгої переваги
    mrs = np.array(strict(r))
    max_values = []
    nd_values = []
    opt = []
    # побудова нечіткої підмножини недомінованих альтернатив,асоційованої з R
    for i in range(len(r)):
        max_values.append(max(mrs[:, i]))
    for i in range(len(mrs)):
        nd_values.append(1 - max_values[i])
    # вибір найкращої альтернативи
    for i in range(len(nd_values)):
        if nd_values[i] == max(nd_values):
            opt.append(i + 1)
    range_of_alt = np.argsort(nd_values)
    range_of_alt = range_of_alt[::-1]
    print("Ранжування:")
    for i in range_of_alt:
        print(i + 1, end=' ')
    print()
    if len(opt) > 1:
        print("Найбільш переважні альтернативи:")
        for i in opt:
            print(i + 1, end=' ')
    else:
        print("Найбільш переважна альтернатива:")
        print(opt[0])


one_expert_decision(expert1)


# %%
def multiple_expert_decision(m1, m2, m3, m4, m5, weights):
    mp = np.empty((len(m1), len(m1)))
    mq = np.empty((len(m1), len(m1)))
    pmax_values = []
    pnd_values = []
    qmax_values = []
    qnd_values = []
    opt = []
    # побудова згортки відношень переваг експертів
    for i in range(len(m1)):
        for j in range(len(m1)):
            mp[i][j] = min(m1[i][j], m2[i][j], m3[i][j], m4[i][j], m5[i][j])
    # побудова НВ строгої переваги для р
    mps = np.array(strict(mp))
    # побудова нечіткої підмножини недомінованих альтернатив,асоційованої з P
    for i in range(len(m1)):
        pmax_values.append(max(mps[:, i]))
    for i in range(len(m1)):
        pnd_values.append(1 - pmax_values[i])
    # побудова опуклої згортки відношень
    e1 = weights[0] * np.array(m1)
    e2 = weights[1] * np.array(m2)
    e3 = weights[2] * np.array(m3)
    e4 = weights[3] * np.array(m4)
    e5 = weights[4] * np.array(m5)
    for i in range(len(m1)):
        for j in range(len(m1)):
            mq[i][j] = e1[i][j] + e2[i][j] + e3[i][j] + e4[i][j] + e5[i][j]

    # побудова НВ строгої переваги для q
    mqs = np.array(strict(mq))
    # побудова нечіткої підмножини недомінованих альтернатив,асоційованої з Q
    for i in range(len(m1)):
        qmax_values.append(max(mqs[:, i]))
    for i in range(len(mqs)):
        qnd_values.append(1 - qmax_values[i])
    # побудова перетину отриманих множин недомінованих альтернатив
    pqnd = min_values_vector(pnd_values, qnd_values)
    # вибір найкращої альтернативи
    for i in range(len(pqnd)):
        if pqnd[i] == max(pqnd):
            opt.append(i + 1)
    range_of_alt = np.argsort(pqnd)
    range_of_alt = range_of_alt[::-1]
    range_of_opt = np.sort(opt)
    range_of_opt = range_of_opt[::-1]
    print("Ранжування:")
    for i in range_of_alt:
        print(i + 1, end=' ')
    print()
    if len(opt) > 1:
        print("Найбільш переважні альтернативи:")
        for i in range_of_opt:
            print(i, end=' ')
    else:
        print("Найбільш переважна альтернатива:")
        print(opt[0])
    print("MpqND:")
    for i in pqnd:
        print(i, end=' ')


multiple_expert_decision(expert1, expert2, expert3, expert4, expert5, weights)


# %%

# виведення результатів завдання 1
def print_task1(r, s):
    rs_association = association(r, s)
    rs_intersection = intersection(r, s)
    r_complement = complement(r)
    s_complement = complement(s)
    rs_composition = composition(r, s)
    r_alpha_level_05 = alpha_level(r, 0.5)
    r_alpha_level_09 = alpha_level(r, 0.9)
    r_strict = strict(r)
    for i in range(len(r_strict)):
        for j in range(len(r_strict)):
            r_strict[i][j] = round(r_strict[i][j])
    r_indifference = indifference(r)
    r_quasi_equivalence = quasi_equivalence(r)
    print("Об'єднання R1 та R2:\n")
    for i in rs_association:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Перетин R1 та R2:\n")
    for i in rs_intersection:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Доповнення R1:\n")
    for i in r_complement:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Доповнення R2:\n")
    for i in s_complement:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Композиція R1 та R2:\n")
    for i in rs_composition:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Альфа-рівень 0.5 відношення R1:\n")
    for i in r_alpha_level_05:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Альфа-рівень 0.9 відношення R1:\n")
    for i in r_alpha_level_09:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Відношення строгої переваги для R1:\n")
    for i in r_strict:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Відношення байдужості для R1:\n")
    for i in r_indifference:
        for j in i:
            print(j, end=' ')
        print()
    print("________________________________________")
    print("Відношення квазіеквівалентності для R1:\n")
    for i in r_quasi_equivalence:
        for j in i:
            print(j, end=' ')
        print()


def print_properties(r1, r2):
    r1_strong_ref, r1_slight_ref = reflexivity(r1)
    r2_strong_ref, r2_slight_ref = reflexivity(r2)
    r1_strong_antiref, r1_slight_antiref = antireflexivity(r1)
    r2_strong_antiref, r2_slight_antiref = antireflexivity(r2)
    r1_sym = symmetry(r1)
    r2_sym = symmetry(r2)
    r1_antisym = antisymmetry(r1)
    r2_antisym = antisymmetry(r2)
    r1_asym = asymmetry(r1)
    r2_asym = asymmetry(r2)
    r1_strong_con, r1_slight_con = connectivity(r1)
    r2_strong_con, r2_slight_con = connectivity(r2)
    r1_trans = transitivity(r1)
    r2_trans = transitivity(r2)
    print("ВЛАСТИВОСТІ")
    print("________________________________________")
    print("Рефлексивність:")
    print()
    print("Відношення R1:")
    if r1_strong_ref:
        print("Має властивість сильної рефлексивності")
    elif r1_slight_ref:
        print("Має властивість слабкої рефлексивності")
    else:
        print("Не має властивості рефлексивності")
    print("Відношення R2:")
    if r2_strong_ref:
        print("Має властивість сильної рефлексивності")
    elif r2_slight_ref:
        print("Має властивість слабкої рефлексивності")
    else:
        print("Не має властивості рефлексивності")
    print("________________________________________")
    print("Антирефлексивність:")
    print()
    print("Відношення R1:")
    if r1_strong_antiref:
        print("Має властивість сильної антирефлексивності")
    elif r1_slight_antiref:
        print("Має властивість слабкої антирефлексивності")
    else:
        print("Не має властивості антирефлексивності")
    print("Відношення R2:")
    if r2_strong_antiref:
        print("Має властивість сильної антирефлексивності")
    elif r2_slight_antiref:
        print("Має властивість слабкої антирефлексивності")
    else:
        print("Не має властивості антирефлексивності")
    print("________________________________________")
    print("Симетричність:")
    print()
    print("Відношення R1:")
    if r1_sym:
        print("Має властивість сильної симетричності")
    else:
        print("Не має властивості симетричності")
    print("Відношення R2:")
    if r2_sym:
        print("Має властивість сильної симетричності")
    else:
        print("Не має властивості симетричності")
    print("________________________________________")
    print("Антисиметричність:")
    print()
    print("Відношення R1:")
    if r1_antisym:
        print("Має властивість сильної антисиметричності")
    else:
        print("Не має властивості антисиметричності")
    print("Відношення R2:")
    if r2_antisym:
        print("Має властивість сильної антисиметричності")
    else:
        print("Не має властивості антисиметричності")
    print("________________________________________")
    print("Асиметричність:")
    print()
    print("Відношення R1:")
    if r1_asym:
        print("Має властивість сильної асиметричності")
    else:
        print("Не має властивості асиметричності")
    print("Відношення R2:")
    if r2_asym:
        print("Має властивість сильної асиметричності")
    else:
        print("Не має властивості асиметричності")
    print("________________________________________")
    print("Зв'язність:")
    print()
    print("Відношення R1:")
    if r1_strong_con:
        print("Має властивість сильної зв'язності")
    elif r1_slight_con:
        print("Має властивість слабкої зв'язності")
    else:
        print("Не має властивості зв'язності")
    print("Відношення R2:")
    if r2_strong_con:
        print("Має властивість сильної зв'язності")
    elif r2_slight_con:
        print("Має властивість слабкої зв'язності")
    else:
        print("Не має властивості рефлексивності")
    print("________________________________________")
    print("Транзитивність:")
    print()
    print("Відношення R1:")
    if r1_trans:
        print("Має властивість сильної транзитивності")
    else:
        print("Не має властивості транзитивності")
    print("Відношення R2:")
    if r2_trans:
        print("Має властивість сильної транзитивності")
    else:
        print("Не має властивості транзитивності")
