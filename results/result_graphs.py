#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Dec 11 20:31:56 2017

@author: anilosmantur
"""

#from graphviz import Source

FILE_PATH = 'withElitizm/pop300/'
FILE_NAME = FILE_PATH + 'graph.stat'


with open(FILE_NAME) as f:
    graph_src = ''
    i = 0
    for line in f:
        if ('-----------------------' in line) or ('GENERATION' in  line):
            continue
        elif line == '\n':
            #Source(graph_src)
            with open(FILE_PATH + 'graphs/' + 'graph_' +str(i)+'.gv', 'w' ) as w:
                w.write(graph_src)
            i += 1
            graph_src = ''
        else:
            graph_src += line