import matplotlib.pyplot as plt  
import pandas as pd
from sklearn.cluster import KMeans  #importing K Means

X=pd.read_csv("Clusters.csv")       #reading the file

kmeans = KMeans(n_clusters=5)       #fitting the model
kmeans.fit(X) 
axes = plt.gca()
#axes.set_xlim([1920,1995])
#axes.set_xlim([1996,2000])
#axes.set_xlim([1986,1995])         #check for each cluster to analyze
#axes.set_xlim([2001,2004])
#axes.set_xlim([2005,2009])
plt.scatter(X.iloc[:,0],X.iloc[:,1], c=kmeans.labels_, cmap='rainbow')  #display the cluster plot