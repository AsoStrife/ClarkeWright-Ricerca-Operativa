# RO Project Clarke&Wright

In this readme we are going to explain our solution to the problem.

The project will be implemented in Java.

```java
common(){
    nodes = createNodesList()
    routes = createMainRoutes()
    distanceMatrix[][]
    savingMatrix[][]


    foreach(nodes as node){
        distanceMatrix[i][j] = sqrt(pow(node[i].x - node[j].x) + pow(node[i].y - node[j].y))

        savingsMatrix[i][j] = distanceMatrix[0][i] + distanceMatrix[0][j] - distanceMatrix[i][j]
    }

    sort(savingsMatrix) 
}
```

## Sequential version  

Starting from the instances: 

- create the list of nodes
- compute savings s(i,j) and order them in non-increasing fashion
- create n routes (0,i,0)
- for each route, find another route which contains (k,0) or (0,l) and merge them
- continue until no merge route is possible


```java
ClarkeWrightSequential(){
    
common()

foreach(routes as node){
    foreach(savingList as s){
        routeToMerge = find => s(k,i) or s(j,l)
        if route.contains((k,0) or (0,l)) && 
        if route.demand + routeToMerge.demand < vehicleCapacity
            mergeRoute(route, routeToMerge)
    }
    else route.next
}
```
## Parallel version

The first three steps are equal to the sequential version. Then:

- from the top of savings list, check if two routes contain (0,j) and (i,0)
- if so, merge them in (i,j) then delete (0,j) and (i,0)

```java
ClarkeWrightParallel(){
    
common()

foreach(savingsList as s){
	route1 = s[0]
	route2 = s[1]
	
	if(route1.contains((0,j)) && route2.contains((i,0))) &&
	if(route1.demand + route2.demand < vehicleCapacity){
		mergeRoute(route1, route2)
     }
}
```

