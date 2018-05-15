# RO Project Clarke&Wright

In this readme we are going to explain our solution to the problem.

The project will be implemented in Java.

## Sequential version  

Starting from the instances: 

- create the list of nodes
- compute savings s(i,j) and order them in non-increasing fashion
- create n routes (0,i,0)
- for each route, find another route which contains (k,0) or (0,l) and merge them
- continue until no merge route is possible

```
begin
nodes = list()
	while(nodes != isEmpty())
		c(i,j) = sqrt(pow(i.x - j.x) + pow(i.y - j.y))
		routes = (0,i,0) for i = 0,...,n
    	s(i,j) = c(0,i) + c(0,j) - c(i,j)
    	sort(list(s(i,j)))
    	routes.foreach(route =>	{
            find s(k,i) or s(j,l)
            if route.contains((k,0) or (0,l))
            	merge current with route
            else route.next
    	})
    	end foreach 
    end while
end 
```
## Parallel version

The first three steps are equal to the sequential version. Then:

- from the top of savings list, check if two routes contain (0,j) and (i,0)
- if so, merge them in (i,j) then delete (0,j) and (i,0)

```
begin
nodes = list()
	while(nodes != isEmpty())
		c(i,j) = sqrt(pow(i.x - j.x) + pow(i.y - j.y))
		routes = (0,i,0) for i = 0,...,n
    	s(i,j) = c(0,i) + c(0,j) - c(i,j)
    	sort(list(s(i,j)))
    	for(i = list.top; i < list.length; i++)	{
            current = s(i,j)
            if(route1.contains((0,j)) && route2.contains((i,0)))	{
                create (i,j)
                delete (0,j), (i,0)
            }
            end if
    	}
    	end for
    end while
end
```

