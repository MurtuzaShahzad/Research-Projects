import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
from sklearn.preprocessing import LabelEncoder, OneHotEncoder


#file read
dataset = pd.read_csv('projectDataset_top_20.csv')

# calculate the correlation matrix
corr = dataset.corr()


# plot the heatmap
fig, ax = plt.subplots(figsize=(8,8))  
sns.heatmap(corr, 
        xticklabels=corr.columns,
        yticklabels=corr.columns)

#columns not required are dropped
dataset = dataset.drop('journalname', axis=1)
dataset = dataset.drop('titleofpaper', axis=1)
dataset = dataset.drop('publish_date', axis=1)
dataset = dataset.drop('latestPost', axis=1)
dataset = dataset.drop('facebook_ID', axis=1)
dataset = dataset.drop('DOI_ID', axis=1)
dataset = dataset.drop('alt_id', axis=1)
#dataset = dataset.drop('like', axis=1)
#dataset = dataset.drop('haha', axis=1)
#dataset = dataset.drop('wow', axis=1)
#dataset = dataset.drop('sad', axis=1)
#dataset = dataset.drop('love', axis=1)


#Reactions: love, haha, wow, anger, sad
reactions = ["love", "haha", "wow", "anger", "sad"]
i = 0

#loop for all reactions
while i < len(reactions):
    X = dataset.iloc[:, dataset.columns != reactions[i]].values
    y = dataset.iloc[:, dataset.columns == reactions[i]].values   
    #print(reactions[i])
    

    #Encoder
    labelencoder_X = LabelEncoder()
    X[:, 12] = labelencoder_X.fit_transform(X[:, 12])
    X[:, 13] = labelencoder_X.fit_transform(X[:, 13])
    onehotencoder = OneHotEncoder(categorical_features = [12])
    onehotencoder1 = OneHotEncoder(categorical_features = [13])
    #onehotencoder
    X = onehotencoder.fit_transform(X).toarray()
    X = onehotencoder1.fit_transform(X).toarray()

    #Avoiding Dummy Variable Trap
    X = X[:, 1:]

    #split
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2,
                                                        random_state = 0)


    #Fitting Multiple Linear Regression 
    regressor = LinearRegression()
    regressor.fit(X_train, y_train)
    
    #Predicitng the Test set results
    y_pred = regressor.predict(X_test)

    #Comaprison of Predicited and Actual Result
    compare = pd.DataFrame(data=y_pred)
    compare[1] = pd.DataFrame(data=y_test)
    compare.columns = ['Predicted', 'Actual']
    compare.Predicted = compare.Predicted.astype(int)
    
    
    #print(compare)
    
    """
    import statsmodels.formula.api as sm
    X = np.append(arr = np.ones((30677,1)).astype(int), values = X, axis = 1)
    """
    
    #Accuracy Information
    def printinfo():
        print("\n Facebook Reaction: " + reactions[i] + "\n")
        print("Mean Absolute Error and Mean Squared Error")
        print("A value of 0 indicates no error or perfect predictions for the fitted model.\n")
        print("Mean Absolute Error: %.2f" % mean_absolute_error(y_test, y_pred))
        print("Mean Squared Error: %.2f" % mean_squared_error(y_test, y_pred) + "\n")
        print("The R^2 (or R Squared) metric provides an indication of the goodness of fit of a set of predictions to the actual values.")
        print("This is a value between 0 and 1 for no-fit and perfect fit respectively.\n")
        print("R Squared : %.2f" % r2_score(y_test, y_pred))

    printinfo()
        
    #Store comparison result in a file
    if(i == 0):
        a = open('scores.txt', 'w')
    else:
        a = open('scores.txt', 'a+')
        a.write(str("\n"))

    a.write(str("Facebook Reaction:" + reactions[i] + "\n\n"))
    a.write(str(compare))
    a.write("\n")
    a.close()
    i+=1

#Scatter plot
fig, ax = plt.subplots()
ax.scatter(y_test, y_pred, edgecolors=(0, 0, 0))
ax.plot([y_test.min(), y_test.max()], [y_pred.min(), y_pred.max()], 'k--', lw=4)
ax.set_xlabel('Measured')
ax.set_ylabel('Predicted')
plt.show()
