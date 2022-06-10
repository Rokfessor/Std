predicates

  motion_picture(symbol) 
  it_is(symbol) 
  positive(symbol, symbol) 
  negative(symbol, symbol) 
  ask(symbol,symbol,symbol) 
  remember(symbol,symbol,symbol) 
  clear_facts 
  run 

database
  dpositive(symbol,symbol)
  dnegative(symbol,symbol)

goal
  clear_facts,
  run.

clauses
   motion_picture(ted) :- 
      it_is(film), 
      it_is(comedy), 
      positive(does, make_people_laugh), 
      positive(has, usa). 

   motion_picture(the_fast_and_the_furious) :-             
      it_is(franchise),
      it_is(action_movie), 
      positive(does, shootouts_and_fights),    
      positive(has, usa). 

   motion_picture(insidious) :-                           
      it_is(horror),        
      it_is(film),       
      positive(does, scare),        
      positive(has, canada).     

   motion_picture(money_heist) :-               
      it_is(series),
      it_is(crime),                        
      positive(does, robbery).      

   motion_picture(the_witcher) :-             
      it_is(series),
      it_is(fantasy)
      positive(has, magic).     

   motion_picture(vikings) :-               
      it_is(series),
      it_is(history),               
      positive(has, history_facts).   

   motion_picture(you) :-             
      it_is(thrill),
      it_is(film),
      positive(has, anxious_waiting).
   
   it_is(comedy) :- 
      positive(does, using_jokes). 
   it_is(comedy) :- 
      positive(does, using_funny_situations).
   it_is(horror) :- 
      positive(does, using_screamers). 
   it_is(horror) :- 
      positive(does, using_scary_moments). 
   it_is(action_movie) :- 
      positive(does, using_electronic_instruments).   
   it_is(action_movie) :- 
      positive(does, using_dynamics).   


   positive(X, Y) :-
     dpositive(X, Y), !.
   positive(X, Y) :-
     not(dnegative(X, Y)),
     ask(X, Y, yes),!.
   positive(X, Y) :-
     not(dnegative(X, Y)),
     remember(X,Y,no),!,fail.

   negative(X, Y) :-
      dnegative(X, Y), !.
   negative(X, Y) :-
      not(dpositive(X, Y)),
      ask(X, Y, no),!.
   negative(X, Y) :-   
      not(dpositive(X, Y)),
      remember(X,Y,yes),!,fail.

   ask(X, Y, yes) :-  !, write(X, " it ", Y, '\n'),
      readln(Reply),
      upper_lower(Reply,Reply1),
      frontchar(Reply1,  'y', _),
      remember(X, Y, yes),!.

   ask(X, Y, no) :-  !, write(X, " it ", Y, '\n'),
      readln(Reply),
      upper_lower(Reply,Reply1),
      frontchar(Reply1, 'n', _),
      remember(X, Y, no),!.

   remember(X, Y, yes) :- assertz(dpositive(X,Y)),!.
   remember(X, Y, no)  :- assertz(dnegative(X,Y)),!.

   clear_facts:-
      retractall(_, dbasedom).

    run :-
      motion_picture(X), !,
      write("\nYour motion picture should be ", X),
      nl, nl, save("motion_picture.dat"),clear_facts,
      write("Press space to escape" ),readchar(_).

   run :-
      write("\nYour motion_picture cannot "),
      write(" be identified.\n\n"),save("zxc.dat"),
      clear_facts, write("Press space to escape" ),
      clearwindow,
      readchar(_).
