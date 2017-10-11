# Deep Neural Network

## Train/Dev/Test

Previous: 70/30 or 60/20/20

Big Data: 98/1/1 (1, 000, 000 / 10, 000 / 10, 000)

- Make sure dev & test is from the same distribution
- Not havin ga test set might be ok: train/dev set

## Bias/Variance

Underfitting -> just right -> overfitting

High Bias? 

- Training set performance -> bigger network/train longer/NN architecture search

High Variance? 

- from train to dev -> more data/regularization/NN architecture search

worst: high bias & high variance

## Regularization

- L2: J += \lambda/2m * ||w||_2^2

- L1: J += \lambda/2m* ||w||_1

  lambda: regularization parameger

  w = w - alpha * lambda / m * w

- Inverted Dropout: a smaller network

  ``` python
  keep_prob = 0.8
  d3 = np.random.rand(a3.shape[0], a3.shape[1]) < keep_prob
  a3 = np.multiply(a3, d3)
  a3 /= keep_prob
  ```

  - Prediction/test: no dropout
  - Intuition: can't rely on any one feature, so have to spread out weights -> shrinking the weights
  - set lower keep_prob for layer which w matrix is too big
  - Default for computer vision: don't have enough data so always overfitting
  - at each iteration, you train a different model that uses only a subset of your neurons
  - Cost function not stricly decreasing

- Data Augmentation

  - Flip image horizontally
  - Take random distortions
  - Collect more data

- Early Stopping: plot dev and training cost function, stop when dev error starts to increase

## Normalization

- subtract mean, devide by deviation
- normalize test data with the same mean & deviation

## Initialization weights

- The weights W[l] should be initialized randomly to break symmetry.
- The cost starts very high. This is because with large random-valued weights, the last activation (sigmoid) outputs results that are very close to 0 or 1 for some examples, and when it gets that example wrong it incurs a very high loss for that example. Indeed, when log(a[3])=log(0)log⁡(a[3])=log⁡(0), the loss goes to infinity.
- Poor initialization can lead to vanishing/exploding gradients, which also slows down the optimization algorithm.
- He initialization works well for networks with ReLU activations.