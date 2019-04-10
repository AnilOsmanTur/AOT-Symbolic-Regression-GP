#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Dec 11 20:13:18 2017

@author: anilosmantur
"""

import matplotlib.pyplot as plt

FILE_PATH = 'withoutElitizm/pop1024/'
FILE_NAME = FILE_PATH + 'info.stat'

best_fitnesses = []
avg_fitnesses = []
with open(FILE_NAME) as f:
    for line in f:
        words = line.split()
        best_fitnesses.append(float(words[0]))
        avg_fitnesses.append(float(words[1]))


plt.plot(best_fitnesses, label="Best Fitness")
plt.plot(avg_fitnesses, label="Avg Fitness")
plt.title('Best and Avg Fitness')
plt.ylabel('Fitness')
plt.xlabel('Generations')
plt.legend()
plt.savefig(FILE_PATH + 'std_fitness.png')
plt.close()
