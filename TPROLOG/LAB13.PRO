predicates

  musician_is(symbol)
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
   musician_is(joyride) :-             
      it_is(solo_artist),
      it_is(electronic),
      it_is(does, sampling_cars_sound),
      positive(has, red_head).      

   musician_is(skrillex) :-             
      it_is(solo_artist),
      it_is(electronic), 
      positive(does, did_dubstep)    
      positive(has, long_hair). 

   musician_is(slipknot) :-                           
      it_is(group),        
      it_is(rocker),       
      positive(does, agressive_music),        
      positive(has, mask).     

   musician_is(lana_del_ray) :-               
      it_is(solo_artist),
      it_is(indie_rocker),                        
      positive(does, melancholic_music).      

   musician_is(jungle) :-             
      it_is(group),
      it_is(indie_rocker)
      positive(has, more_than_one_singer).     

   musician_is(gorillaz) :-               
      it_is(group),
      it_is(indie_rocker),               
      positive(has, mask).   

   musician_is(kendrick_lamar) :-             
      it_is(solo_artist),
      it_is(rapper),
      positive(does, agressive_music).

   musician_is(asap_rocky) :-            
      it_is(solo_artist),
      it_is(rapper),
      positive(does, flex_music). 

   musician_is(bringMeTheHorizon) :-          
      it_is(group),    
      it_is(rocker),              
      positive(does, agressive_music),
      positive(does, mixing_rock_and_electronic).     

   musician_is(jCole) :-                             
      it_is(solo_artist),  
      it_is(rapper),                         
      positive(does, melancholic_music).         

   musician_is(asking_alexandria) :-            
      it_is(group),
      it_is(rocker),                
      positive(does, agressive_music). 
   
   it_is(rocker) :- 
      positive(does, using_guitar).
   it_is(rocker) :- 
      positive(does, using_drums).
   it_is(rapper) :- 
      positive(does, sampling_other_music). 
   it_is(rapper) :- 
      positive(does, lyrical_music_texts). 
   it_is(electronic) :- 
      positive(does, using_electronic_instruments).   
   it_is(electronic) :- 
      positive(does, sampling_other_music).   
   it_is(indie_rocker) :- 
      it_is(rocker),
      positive(does, relaxing_music).   
   
   it_is(group) :- 
      positive(has, more_than_one_member).

   it_is(solo_artist) :-
      not(it_is(group)).

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
      musician_is(X), !,
      write("\nYour musician should be ", X),
      nl, nl, save("musician.dat"),clear_facts,
      write("Press space to escape" ),readchar(_).

   run :-
      write("\nYour musician cannot "),
      write(" be identified.\n\n"),save("aaa.dat"),
      clear_facts, write("Press space to escape" ),
      clearwindow,
      readchar(_).
