 /*                  " ������������ "                             */  

predicates
  p(symbol,symbol)
  fem(symbol)
  male(symbol)
  
  mother(symbol,symbol)
  grandmother(symbol,symbol)
  %father(symbol,symbol)
  %grandfather(symbol,symbol)
  sister(symbol,symbol)
  %bro(symbol,symbol)
  aunt(symbol,symbol)
  %uncle(symbol,symbol)
  %niece(symbol,symbol)
  %nephew(symbol,symbol)
  ancestor(symbol,symbol)
  
 
 goal
  sister(beth,X),write(X),nl,fail. 

clauses
 p(mary,bob).         % ��� - த�⥫� ����
 p(mary,beth).
 p(tom,beth).
 p(tom,bob).
 p(tom,liz).
 p(bob,ann).
 p(bob,pat).
 p(pat,jim).
 
 fem(mary).              % ��� - ���騭�
 fem(beth).
 fem(liz).
 fem(ann).
 fem(pat).
 
 male(tom).              % ��� - ��稭�
 male(bob).
 male(jim).
 
 mother(X,Y):-           % X - ���� Y 
       p(X,Y),
       fem(X).
 
 grandmother(X,Y):-      % X - ����誠 Y 
       p(X,Z),
       p(Z,Y),  
       fem(X).
 
             
 sister(X,Y):-           % X - ���� Y
       p(Z,X),
       p(Z,Y),
       fem(X),
       X<>Y .    

 aunt(X,Y):-             % X - ��� Y   
       sister(X,Z),
       p(Z,Y).

 /*niece(X,Y):-          % X - ����ﭭ�� Y (���)   
       aunt(Y,X),
       fem(X).
 niece(X,Y):-            % X - ����ﭭ�� Y (�廊)  
       uncle(Y,X),
       fem(X). */
        
 ancestor(X,Z):-         % X - �।�� Z
       p(X,Z).
 ancestor(X,Z):-
       p(X,Y),
       ancestor(Y,Z).	              
 











    
