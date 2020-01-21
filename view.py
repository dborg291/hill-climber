import matplotlib.pyplot as plt
import numpy as np

f = open('landscape.txt')
w = int(f.readline())
h = int(f.readline())

x = [int(i) for i in f.readline().split()]
for i in range(len(x)):
	if x[i] > 70:
		x[i] += 100
x = np.reshape(x, (w, h))

# plt.imshow(x, cmap='gist_earth', interpolation='nearest' )
plt.imshow(x, cmap='terrain', interpolation='nearest' )
plt.show()