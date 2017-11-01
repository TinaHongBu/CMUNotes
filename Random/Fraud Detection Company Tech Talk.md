# Fraud Detection Company Tech Talk

## Challenge

Transaction throughput: way above average

2000 transactions per sec, each has to be processed in 100 ms

Percentile: 99.999% accuracy based on SLA

So the average processing time is much lower than 100 ms (around 1 ms)

-> heavy caching -> in memory processing