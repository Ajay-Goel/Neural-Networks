# Neural-Networks
# INFO_6205_Program_Structures_-_Algorithm
## Project - Digit and Face Recognition Neural Network

Team Member - Ajay Goel, Akshay N. Mahajanshetti

## Introduction
#### Artificial neural networks (ANNs) 
or connectionist systems are computing systems which perform tasks by
considering examples, generally without being programmed with any task-specific rules.
For example, In image recognition, system might learn to identify images that contain cats by analyzing training
images that have been manually labelled as "cat" or "no cat" and using the results to identify cats in other images.
It does this without any prior knowledge about cats. The system learns things like cats have fur, tails, whiskers
and other cat attributes from the training data.

An ANN is based on a collection of connected units or nodes which can transmit a signal from one artificial
neuron to another. An artificial neuron that receives a signal can process it and then signal additional artificial
neurons connected to it. Artificial neurons and edges typically have a weight that adjusts as learning proceeds.
The weight increases or decreases the strength of the signal at a connection. Different layers may perform
different kinds of transformations on their inputs. Signals travel from the first layer (the input layer), to the last
layer (the output layer), possibly after traversing the layers multiple times.

![alt text](https://github.com/Ajay-Goel/INFO_6205_Program_Structures_-_Algorithm/blob/FinalProject/Face_Digit_Recognition_Project/Media/BPNeuralNetwork.jpg)

## Problem Statement
The neural network in our application is used to solve two major problems:
### Digit Recognition
In this problem, The idea is to take a large number of handwritten digits (in excel format). In our
system, we have taken data set of 42000 digits to train the system then develop a system which can
learn from those training examples, we test it by passing 11000 testing digits to the system and at least
85% digits should be predicted correctly.
Furthermore, by increasing the number of training examples, the network can learn more about
handwriting, and so improve its accuracy.

### Face Recognition
In this problem, our aim is to pass images (.png format) of 10 different people in 5 different angles
and train the system. After this, to see if the system has learnt to identify different
people, we test it by passing close to 35 images to the system and at least 80%
images should be recognised correctly.

## Impementation

Project source code in the project/src folder.

A detailed report of this project and including basics about Neural Networks in file named: Project_Report.pdf

A short presentation to get the understating of working of the project with code and output snippets in file named: Project Presentation.pptx
