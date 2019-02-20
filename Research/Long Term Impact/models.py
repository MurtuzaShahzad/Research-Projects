import pandas as pd
from pandas import DataFrame
import sklearn
from sklearn.naive_bayes import BernoulliNB
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
from sklearn.preprocessing import StandardScaler

df = pd.read_csv('c.csv')

X = DataFrame(df.iloc[:,0:10])
y = DataFrame(df.iloc[:,10])

#traindata,testdata,traintarget,testtarget = train_test_split(data, target, test_size=0.25)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.20, random_state=42)

#feature scaling
sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)

'''
Logistic regression with grid search
'''
from sklearn.linear_model import LogisticRegression
classifier = LogisticRegression(max_iter=50, multi_class='multinomial', penalty='l2',solver='saga', tol= 1.0, random_state = 42)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


# Applying Grid Search to find the best model and the best parameters
from sklearn.model_selection import GridSearchCV
parameters = [
                    {
                            'penalty': ['l1'], 'tol': [1.0,2.0,3.0,4.0,5.0], 'solver':['liblinear', 'saga'],'max_iter':[50,100,150,200], 'multi_class':['ovr']
                    },
                    {
                            'penalty': ['l1'], 'tol': [1.0,2.0,3.0,4.0,5.0], 'solver':['saga'],'max_iter':[50,100,150,200], 'multi_class':['ovr', 'multinomial']
                    },

                    {
                            'penalty': ['l2'], 'tol': [1.0,2.0,3.0,4.0,5.0], 'solver':['newton-cg', 'lbfgs','liblinear', 'sag', 'saga'],'max_iter':[50,100,150,200], 'multi_class':['ovr']
                    },
                    
                    {
                            'penalty': ['l2'], 'tol': [1.0,2.0,3.0,4.0,5.0], 'solver':['newton-cg', 'lbfgs', 'sag', 'saga'],'max_iter':[50,100,150,200], 'multi_class':['ovr', 'multinomial']
                    }
                ]
grid_search = GridSearchCV(estimator = classifier,
                           param_grid = parameters,
                           scoring = 'accuracy',
                           cv = 10,
                           n_jobs = -1)
grid_search = grid_search.fit(X_train, y_train)
best_accuracy = grid_search.best_score_
best_parameters = grid_search.best_params_
print("Best accuracy",best_accuracy)
print("Best parameters",best_parameters)





'''
KNN with grid search
'''
from sklearn.neighbors import KNeighborsClassifier
classifier = KNeighborsClassifier(n_neighbors = 15, metric = 'minkowski', p = 2)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


# Applying Grid Search to find the best model and the best parameters
from sklearn.model_selection import GridSearchCV
parameters = [
                    {
                            'n_neighbors': [2,5,8,11,14,17], 'algorithm': ['auto', 'ball_tree', 'kd_tree', 'brute'], 'leaf_size':[26,28,30,32,34],'p':[1,2,3]
                    }
            ]
grid_search = GridSearchCV(estimator = classifier,
                           param_grid = parameters,
                           scoring = 'accuracy',
                           cv = 10,
                           n_jobs = -1)
grid_search = grid_search.fit(X_train, y_train)
best_accuracy = grid_search.best_score_
best_parameters = grid_search.best_params_
print("Best accuracy",best_accuracy)
print("Best parameters",best_parameters)




from sklearn.svm import SVC
classifier = SVC(kernel = 'linear', random_state = 0)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


from sklearn.svm import SVC
classifier = SVC(kernel = 'rbf', random_state = 0)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)




'''
Naive Bayes does not have Grid Search
'''

from sklearn.naive_bayes import GaussianNB
classifier = GaussianNB()
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)



'''
Decision Tree with Grid Search
'''

from sklearn.tree import DecisionTreeClassifier
classifier = DecisionTreeClassifier(criterion = 'entropy', random_state = 0)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


# Applying Grid Search to find the best model and the best parameters
from sklearn.model_selection import GridSearchCV
parameters = [
                    {
                            'criterion': ['gini','entropy'], 'splitter':['best','random'], 'max_depth':[2,5,10,50], 'min_samples_split':[2,3,4,5,10], 'min_samples_leaf':[1,2,3,4,5,10], 'min_weight_fraction_leaf':[0,0.1,0.2,0.3,0.4,0.5], 'max_features':['auto','sqrt','log2'], 'max_leaf_nodes':[2,3,5,10] , 'presort':[True,False]
                    }
                ]
grid_search = GridSearchCV(estimator = classifier,
                           param_grid = parameters,
                           scoring = 'accuracy',
                           cv = 10,
                           n_jobs = -1)
grid_search = grid_search.fit(X_train, y_train)
best_accuracy = grid_search.best_score_
best_parameters = grid_search.best_params_
print("Best accuracy",best_accuracy)
print("Best parameters",best_parameters)


'''
Random Forest with Grid Search
'''

from sklearn.ensemble import RandomForestClassifier
classifier = RandomForestClassifier(n_estimators = 10, criterion = 'entropy', random_state = 0)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


# Applying Grid Search to find the best model and the best parameters
from sklearn.model_selection import GridSearchCV
parameters = [
                    {
                          'n_estimators':[1,2,3,4,5], "max_depth": [2,3,4,5,6, None],"max_features": ['auto','sqrt','log2'], 'min_samples_split':[2,3,4], 'min_samples_leaf':[8,9,10,11], "bootstrap": [True, False], "criterion": ["gini", "entropy"]
                    }
                ]
grid_search = GridSearchCV(estimator = classifier,
                           param_grid = parameters,
                           scoring = 'accuracy',
                           cv = 10,
                           n_jobs = -1)
grid_search = grid_search.fit(X_train, y_train)
best_accuracy = grid_search.best_score_
best_parameters = grid_search.best_params_
print("Best accuracy",best_accuracy)
print("Best parameters",best_parameters)


from sklearn.gaussian_process import GaussianProcessClassifier
classifier = GaussianProcessClassifier()
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


'''
MLP with Grid Search
'''
from sklearn.neural_network import MLPClassifier
classifier =MLPClassifier(alpha=1)
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)

# Applying Grid Search to find the best model and the best parameters
from sklearn.model_selection import GridSearchCV
parameters = [
                    {
                        'activation':['identity', 'logistic', 'tanh', 'relu'], 'solver' :['lbfgs', 'adam'], 'max_iter':[150,200,250],'shuffle':[True,False]
                    },
                    {
                        'activation':['identity', 'logistic', 'tanh', 'relu'], 'solver' :['adam'], 'max_iter':[150,200,250],'shuffle':[True,False], 'early_stopping':[True,False]
                    },
                    {
                        'activation':['identity', 'logistic', 'tanh', 'relu'], 'solver' :['sgd'], 'learning_rate':['constant', 'invscaling', 'adaptive'],'learning_rate_init':[0.001,0.01,0.0001],'power_t':[0.3,0.4,0.5],'max_iter':[150,200,250],'shuffle':[True,False], 'momentum':[0.5,0.7,0.9],'nesterovs_momentum':[True,False], 'early_stopping':[True,False]
                    }

                ]
grid_search = GridSearchCV(estimator = classifier,
                           param_grid = parameters,
                           scoring = 'accuracy',
                           cv = 10,
                           n_jobs = -1)
grid_search = grid_search.fit(X_train, y_train)
best_accuracy = grid_search.best_score_
best_parameters = grid_search.best_params_
print("Best accuracy",best_accuracy)
print("Best parameters",best_parameters)


from sklearn.ensemble import AdaBoostClassifier
classifier =AdaBoostClassifier()
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
classifier = QuadraticDiscriminantAnalysis()
classifier.fit(X_train, y_train)
y_pred = classifier.predict(X_test)
print(classification_report(y_test,y_pred))
sklearn.metrics.accuracy_score(y_test, y_pred)


import keras
from keras.models import Sequential
from keras.layers import Dense

# Initialising the ANN
classifier = Sequential()

# Adding the input layer and the first hidden layer
classifier.add(Dense(units = 6, kernel_initializer = 'uniform', activation = 'relu', input_dim = 10))

# Adding the second hidden layer
classifier.add(Dense(units = 12, kernel_initializer = 'uniform', activation = 'relu'))

classifier.add(Dense(units = 18, kernel_initializer = 'uniform', activation = 'relu'))
classifier.add(Dense(units = 8, kernel_initializer = 'uniform', activation = 'relu'))

# Adding the output layer
classifier.add(Dense(units = 1, kernel_initializer = 'uniform', activation = 'sigmoid'))

# Compiling the ANN
classifier.compile(optimizer = 'adam', loss = 'binary_crossentropy', metrics = ['accuracy'])

# Fitting the ANN to the Training set
classifier.fit(X,y , batch_size = 10, epochs = 10)




















































#Decision Tree Classifier
decision = DecisionTreeClassifier(criterion = 'entropy', random_state = 0)
decision.fit(traindata,traintarget)
decisionresult = decision.predict(testdata)
print(classification_report(testtarget,decisionresult))
sklearn.metrics.accuracy_score(testtarget, decisionresult)

#Bernoulli Naive Bayes Classifier
nbayes = BernoulliNB()
nbayes.fit(traindata,traintarget.values.ravel())
nbayesresult = nbayes.predict(testdata)
print(classification_report(testtarget,nbayesresult))
sklearn.metrics.accuracy_score(testtarget, nbayesresult)


#Random Forest Classifier
random = RandomForestClassifier(n_estimators = 10, criterion = 'entropy', random_state = 0)
random.fit(traindata,traintarget.values.ravel())
randomresult = random.predict(testdata)
print(classification_report(testtarget,randomresult))
sklearn.metrics.accuracy_score(testtarget, randomresult)

#logistic regression
lr = LogisticRegression(random_state = 0)
lr.fit(traindata, traintarget.values.ravel())
regres = lr.predict(testdata)
print(classification_report(testtarget,regres))
sklearn.metrics.accuracy_score(testtarget, regres)

'''
#KNN
knn = KNeighborsClassifier(n_neighbors = 5, metric = 'minkowski', p = 2)
knn.fit(traindata, traintarget.values.ravel())
knnres = knn.predict(testdata)
print(classification_report(testtarget,knnres))
'''
#GaussianNB
gnb = GaussianNB()
gnb.fit(traindata, traintarget.values.ravel())
gnbres = gnb.predict(testdata)
print(classification_report(testtarget,gnbres))

#SVM
'''svm = SVC(kernel = 'linear', random_state = 0)
svm.fit(traindata, traintarget.values.ravel())
svmres = svm.predict(testdata)
print(classification_report(testtarget,svmres))
'''



# Importing the Keras libraries and packages
import keras
from keras.models import Sequential
from keras.layers import Dense

# Initialising the ANN
classifier = Sequential()

# Adding the input layer and the first hidden layer
classifier.add(Dense(units = 6, kernel_initializer = 'uniform', activation = 'relu', input_dim = 12))

# Adding the second hidden layer
classifier.add(Dense(units = 6, kernel_initializer = 'uniform', activation = 'relu'))

# Adding the output layer
classifier.add(Dense(units = 1, kernel_initializer = 'uniform', activation = 'sigmoid'))

# Compiling the ANN
classifier.compile(optimizer = 'adam', loss = 'binary_crossentropy', metrics = ['accuracy'])

# Fitting the ANN to the Training set
classifier.fit(traindata,traintarget , batch_size = 10, epochs = 3)

# Part 3 - Making predictions and evaluating the model

# Predicting the Test set results
y_pred = classifier.predict(X_test)
y_pred = (y_pred > 0.5)

# Making the Confusion Matrix
from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test, y_pred)