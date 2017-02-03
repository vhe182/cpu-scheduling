# CPU Scheduling Using Threads and Semaphores

Written by Victor Hugo Estrada  
For Professor Korkmaz  
Operating Systems  
University of Texas at San Antonio

## Summary

This program demonstrates four cpu scheduling algorithms. Particularly:

* FIFO
* SJF
* PR
* RR


<=============Write more for Summary================>

## Quick Start

Want to get scheduling quick? This is the place.

### Arguments

This program requires some command line arguments be passed to it.  
They must be passed in the following manner:

`prog -alg [FIFO|SJF|PR|RR] [-quantum [integer(ms)]] -input [file name]`

So to request RR with quantum of 30ms from input file ./test1.txt looks like:

`prog -alg RR -quantum 30 -input ./test1.txt`

*NOTE: RR is the only scheduling algorithm that requires a quantum.*

### Input File

The input file consists of lines that represent tasks that the program will
translate to processes.  There are three types of tasks that can appear:

1. proc

   1. Interpreted as a process followed by a series of alternating  
      cpu and io bursts
      
   1. A proc with a cpu burst of 10 followed by an io burst of 5 followed  
      by another cpu burst of 35 and finished with an io burst of 20  
      looks like:  
      
      `proc 10 5 35 20`
      
1. sleep

   1. Interpreted as an instruction to put the cpu scheduler thread to sleep.
   1. An example where the cpu scheduler is told to sleep for 10ms looks like:
   
      `sleep 10`
      
1. stop
   1. An instruction that signals the end of instructions.  Signals to the cpu  
      scheduler thread to terminate and the main thread to proceed with  
      termination routine.

### Output

The output of the program looks like:
```
Input File Name : file name
CPU Scheduling Alg : FIFO|SJF|PR|RR (quantum)
CPU utilization : ....
Throughput : ....
Turnaround time : ....
Waiting time : ....
Response time : ....
```

## Synopsis

*My approach to solving the problem...*