import numpy as np
from collections import defaultdict
from functools import reduce

with open("relations.txt", 'r') as data:
    dataset = [[int(x) for x in line.split()] for line in data]

make_R = lambda lst, sz: [lst[i:i + sz] for i in range(0, len(lst), sz)]
R = make_R(dataset, 15)
print("size of R: {}".format(len(R)))


class Graph:
    def __init__(self, vertices):
        self.graph = defaultdict(list)
        self.V = vertices

    def addEdge(self, u, v):
        self.graph[u].append(v)

    def isCyclicUtil(self, v, visited, recStack):
        visited[v] = True
        recStack[v] = True
        for neighbour in self.graph[v]:
            if visited[neighbour] == False:
                if self.isCyclicUtil(neighbour, visited, recStack) == True:
                    return True
            elif recStack[neighbour] == True:
                return True
        recStack[v] = False
        return False

    def isCyclic(self):
        visited = [False] * self.V
        recStack = [False] * self.V
        for node in range(self.V):
            if visited[node] == False:
                if self.isCyclicUtil(node, visited, recStack) == True:
                    return True
        return False


def relationToGraph(matrix):
    g = Graph(15)
    index = 0
    for row in matrix:
        index1 = 0
        for element in row:
            if (element == 1):
                g.addEdge(index, index1)
            index1 += 1
        index += 1
    return g

#пошук к максимальних та к оптимальних повертає масив параметрів
def checkX(S):
    max_k = np.array([])
    opt_k = np.array([])
    for i, x in zip(range(len(S)), S):
        if np.any(S - x > 0):
            continue
        max_k = np.append(max_k, i + 1)
        if np.sum(x) == 15:
            opt_k = np.append(opt_k, i + 1)

    return max_k, opt_k


def k_optimization(Rn):
    I = (Rn == Rn.T) * Rn
    P = Rn - I
    N = (Rn == Rn.T) - I

    for i in range(1, 5):
        if i == 1:
            S1 = I + P + N
            max_1, opt_1 = checkX(S1)
        elif i == 2:
            S2 = P + N
            max_2, opt_2 = checkX(S2)
        elif i == 3:
            S3 = P + I
            max_3, opt_3 = checkX(S3)
        elif i == 4:
            S4 = P
            max_4, opt_4 = checkX(S4)

    parameters = {"1_max": max_1,
                  "1_opt": opt_1,
                  "2_max": max_2,
                  "2_opt": opt_2,
                  "3_max": max_3,
                  "3_opt": opt_3,
                  "4_max": max_4,
                  "4_opt": opt_4}

    return parameters


def printInformation_k_opt(parameters, num_r):
    print("k = 1 ")
    print("max: {}".format(parameters["1_max"]))
    print("opt: {}".format(parameters["1_opt"]))
    print("k = 2 ")
    print("max: {}".format(parameters["2_max"]))
    print("opt: {}".format(parameters["2_opt"]))
    print("k = 3 ")
    print("max:{}".format(parameters["3_max"]))
    print("opt:{}".format(parameters["3_opt"]))
    print("k = 4 ")
    print("max: {}".format(parameters["4_opt"]))
    print("opt: {}".format(parameters["4_opt"]))


def upperSection(r, idx):
    return set(r[:, idx].nonzero()[0])


def Ssets(r):
    S = [set()]
    while S[-1] != set(range(0, len(r))):
        S.append(set([i for i in range(0, len(r)) if upperSection(r, i) - set([i]) <= S[-1]]))
    return S


def morgenstern(r):
    C = Ssets(r)
    elements = np.hstack(map(lambda i: list(C[i] - C[i - 1]), range(1, len(C))))
    return reduce(lambda s, i: s | {i} if not upperSection(r, i) & s else s, elements, set())


def printSet(s):
    return "{" + ", ".join(str(v + 1) for v in list(s)) + "}"


def printResultMar(s):
    return printSet(s)


def main():
    for num_r, r_i in zip(range(len(R)), R):
        Rn = np.array(r_i)
        print("____R{}____".format(num_r))
        graph = relationToGraph(Rn)
        if graph.isCyclic() == 1:
            print("Graph has a cycle")
            print("K - optimization")

            parameters = k_optimization(Rn)
            printInformation_k_opt(parameters, num_r)
        else:
            print("Graph has no cycle")
            print("Morgenstern Optimizaion:{}".format(printResultMar(morgenstern(Rn))))


main()
