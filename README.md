## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  | 10,000,000         | 0.018105        |
| Using ReentrantLock     | 10,000,000         | 0.880317        |
| Synchronized method     | 10,000,000         | 0.829872        |
| AtomicLong for total    | 10,000,000         | 0.275422        |

## 1. Using unsynchronized counter object

| Number of run           | Limit              | total (counter) | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|-----------------|
| #1                      | 1,000              | 0               | 0.000654        |
| #2                      | 1,000              | 0               | 0.000680        |
| #3                      | 1,000              | 5719            | 0.001115        |
| #4                      | 1,000              | 0               | 0.000702        |
| #5                      | 1,000              | -17017          | 0.000537        |
| Average                 | 1,000              | -5649           | 0.000738        |

| Number of run           | Limit              | total (counter) | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|-----------------|
| #1                      | 10,000,000         | -39786587568554 | 0.015294        |
| #2                      | 10,000,000         | -44706490144091 | 0.016695        |
| #3                      | 10,000,000         | -49739124259513 | 0.016983        |
| #4                      | 10,000,000         | -49913425329782 | 0.019457        |
| #5                      | 10,000,000         | 35158416018163  | 0.022098        |
| Average                 | 10,000,000         | -2979744225675  | 0.018105        |

1.3) Explain the results. Why is the counter total sometimes not zero? Why is it not the same each time?
Answer : No, the total are not the same as zero. Because of using the same resource(counter) between each threads make some mistake to that resource.

## 2. Implications for Multi-threaded Applications

How might this affect real applications?
Answer : By example..
A - A person who want to withdraw some money.
B - A person who want to deposit some money of A's account.

A : Checking his balance 	    --- Balance = 100 THB
B : Depositing to A's account   --- Balance = 100 THB
A : Withdrawing 50 THB from ATM --- Balance = 50 THB (remove amount from balance that got from 1st step : 100-50=50)
B : Adding 100 THB to account   --- Balance = 200 THB (add amount to balance that got from 2nd step : 100+100=200)

## 3. Counter with ReentrantLock

| Number of run           | Limit              | total (counter) | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|-----------------|
| #1                      | 10,000,000         | 0               | 0.912670        |
| #2                      | 10,000,000         | 0               | 0.859457        |
| #3                      | 10,000,000         | 0               | 0.889418        |
| #4                      | 10,000,000         | 0               | 0.845900        |
| #5                      | 10,000,000         | 0               | 0.894141        |
| Average                 | 10,000,000         | 0               | 0.880317        |

3.1) Describe the results. Is the total always zero? Record the average runtime in README.md
Answer : The total always zero with the average runtime of 0.880317 second.

3.2) Explain why the results are different from problem 1.
Answer : ReentrantLock helps to lock the resource that there're some threads is using then the another one can't access to it just wait. After that thread finish with the resource, it also unlock to make it free for another threads can use that resource.

3.3) What does a ReentrantLock do? Why (and when) would you use it in a program?
Answer : No, I woundn't use in the program since it lock all resource with/without shared resources.

3.4) Why do we write "finally { lock.unlock(); }" in the code?
Answer : The code tells that the end of program is to unlock the resource even if get in try or not.

## 4. Counter with synchronized method

| Number of run           | Limit              | total (counter) | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|-----------------|
| #1                      | 10,000,000         | 0               | 0.907488        |
| #2                      | 10,000,000         | 0               | 0.785721        |
| #3                      | 10,000,000         | 0               | 0.878716        |
| #4                      | 10,000,000         | 0               | 0.752102        |
| #5                      | 10,000,000         | 0               | 0.825334        |
| Average                 | 10,000,000         | 0               | 0.829872        |

4.1) Describe the results. Is the total 0? Return the average run time in README.md.
Answer : The total always zero with the average runtime of 0.829872 second.

4.2) Explain why the results are different from problem 1.
Answer : Synchronized helps to pause the thread into queue if there are already running thread. So there're no any thread running at the same time.

4.3) What is the meaning of "synchronized"? Why (and when) would you use it in a program?
Answer : No, since it wastes a lot of time in Big-O.

## 5. Counter with AtomicLong

| Number of run           | Limit              | total (counter) | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|-----------------|
| #1                      | 10,000,000         | 0               | 0.307550        |
| #2                      | 10,000,000         | 0               | 0.301747        |
| #3                      | 10,000,000         | 0               | 0.303126        |
| #4                      | 10,000,000         | 0               | 0.229715        |
| #5                      | 10,000,000         | 0               | 0.234974        |
| Average                 | 10,000,000         | 0               | 0.275422        |

5.1) Run the program a few times. AtomicCounter does not use a lock (like problem 3) and the add method isn't synchronized, but it still fixes the error in problem 1. Explain why.
Answer : AtomicCounter is first read the data then write. If there are some threads write the data to the another one, the first threads is not write yet, its read data again until the data is equals then write to it.

5.2) Describe why and when you would use AtomicLong (or AtomicDouble, AtomicInteger) in a program.
Answer : Atomic use when there are many threads that use/not use the resource at the same time. It's help to save time a lot.

## 6. Analysis of Results

6.1) Compare the average run-times of all the solutions. Which one is fastest? Which is slowest?
Answer : Unsynchronized counter > AtomicLong > Synchronized method > ReentrantLock
The fastest is Unsynchronized counter. The slowest is ReentrantLock.

6.2) Which of the above solutions can be applied to the broadest range of problems where you need to ensure that only one thread modifies the resource at any one time? The "resource" could be a lot more complex than adding to a single variable (such as a List).
Answer : AtomicLong is the best solution to solve the problem.

## 7. Using Many Threads (optional)

| Counter class           | Limit              | total (counter) | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|-----------------|
| Unsynchronized counter  | 10,000,000         | -10093777967157 | 0.046536		   |
| Using ReentrantLock     | 10,000,000         | 0               | 3.187872        |
| Synchronized method     | 10,000,000         | 0.              | 5.302975        |
| AtomicLong for total    | 10,000,000         | 0               | 2.694140        |

7) Modify the Main class to create 10 threads (5 adders and 5 subtracters). Record the run times again. Which is the fastest? Which is slowest?
Answer : The fastest is also Unsynchronized counter. The slowest changed from ReentrantLock to Synchronized method.