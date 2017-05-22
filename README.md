# GraphDB

Simple graph database implementation to represent following :

#### Entities 
Person, Business
#### Relations
FriendOf, RelativeOf, WorksAt
#### Example Queries: 
* All relatives of Person(X)
* List the relatives of an every person who works at Business(Y)
* List all business with more than Z employees
* List every person who has friends with employed relatives


## Design
Graph is backed by set of edges in memory. Code need to be improved to handle graph across multiple cluster.

* Abvoe queries are tested in Unit tests and can be executed by running the ‘sbt test’ command.
* nodes cane be added/removed  and  relations between them can be changed at run time.
* Queries could be excuted using basic query DSL provided.


## Reference
https://csperkins.org/research/thesis-msci-waite.pdf
http://letitcrash.com/post/30257014291/distributed-in-memory-graph-processing-with-akka
