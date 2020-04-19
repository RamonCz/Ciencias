%Practica 4
%Cruz Perez Ramon 315008148
%Manitas
%Nito

%Inicio trasformar en forma normal clausular
fnc(v(X),v(X)).
fnc(neg(neg(X)),X).
fnc(neg(v(X)),neg(v(X))).
fnc(imp(X,Y),Z):-
fnc(neg(X),I),
    fnc(Y,J),
    Z=disy(I,J).
fnc(equiv(X,Y),Z):-
    fnc(imp(X,Y),I),
    fnc(imp(Y,X),J),
    Z=conj(I,J).
fnc(neg(conj(X,Y)),Z):-
    fnc(neg(X),I),
    fnc(neg(Y),J),
    Z=disy(I,J).
fnc(neg(disy(Y,X)),Z):-
    fnc(neg(X),I),
    fnc(neg(Y),J),
    Z=disy(I,J).
fnc(neg(imp(X,Y)),Z):-
    fnc(X,I),
    fnc(neg(Y),J),
    Z=conj(I,J).
fnc(neg(equiv(X,Y)),Z):-
    fnc(neg(imp(X,Y)),I),
    fnc(neg(imp(Y,X)),J),
    Z=disy(I,J).
fnc(disy(X,Y),Z):-
    fnc(X,I),
    fnc(Y,J),
    Z=disy(I,J).
fnc(conj(X,Y),Z):-
    fnc(X,I),
    fnc(Y,J),
    Z=conj(I,J).
%Fin Fnc

%Distributividad
dist(disy(v(X),v(Y)),disy(v(X),v(Y))).
dist(disy(neg(v(X)),v(Y)),disy(neg(v(X)),v(Y))).%noestoyseguro
dist(disy(v(X),neg(v(Y))),disy(v(X),neg(v(Y)))).
dist(disy(neg(v(X)),neg(v(Y))),disy(neg(v(X)),neg(v(Y)))).
dist(conj(disy(P,Q),disy(Z,R)),L):-!,
    dist(disy(P,Q),I),
    dist(disy(Z,R),J),
    L=conj(I,J).
dist(conj(disy(P,Q),R),Z):-
    dist(disy(P,R),I),
    dist(disy(Q,R),J),
    Z=conj(I,J).
dist(disy(X,conj(Y,Z)),R):-
    dist(disy(X,Y),I),
    dist(disy(X,Z),J),
    R=conj(I,J).
dist(disy(conj(P,Q),X),R):-
    dist(disy(P,X),I),
    dist(disy(Q,X),J),
    R=conj(I,J).
%Fin Distributividad

%clausular siver para pasar una formula en FNC.
clausular(X,R):-
    fnc(X,Z), %primero se elimina imp y equiv, asi como las negaciones
    dist(Z,R). %Se aplica dist. si es necesario.
%Fin trasformar en forma normal clausular

%lista(l,l), sirve para que dada una formula en clausular la trasforma en conjuentos en este caso en listas de listas.
lista([],[]). % Inicio caso bases
lista([v(X)],[v(X)]).
lista([disy(v(X),v(Y))],[v(X),v(Y)]).
lista([conj(v(X),v(Y))],[v(X),v(Y)]).
lista([disy(neg(v(X)),v(Y))],[neg(v(X)),v(Y)]).
lista([disy(v(X),neg(v(Y)))],[v(X),neg(v(Y))]).
lista([conj(neg(v(X)),v(Y))],[neg(v(X)),v(Y)]).
lista([conj(v(X),neg(v(Y)))],[v(X),neg(v(Y))]).
lista([disy(neg(v(X)),neg(v(Y)))],[neg(v(X)),neg(v(Y))]).
lista([conj(neg(v(X)),neg(v(Y)))],[neg(v(X)),neg(v(Y))]).%Fin casos bases.
lista([disy(X,Y)|T],Z):-!,
    lista([X],I),
    lista([Y],J),
    lista(T,Z1),
    append(I,J,H),
    append(H,Z1,L),
    Z=L.
lista([conj(X,Y)],Z):-!,
    lista([X],I),
    lista([Y],J),
    append([I],[J],H),
    Z=H.
%-.-----------------------------------------
%ejercicio1.
literal(v(_)).
literal(neg(v(_))).
compl(neg(v(X),v(X))).
compl(v(X),neg(v(X))).

%ejercicio2
for_cl(X,Y):-
    clausular(X,Z),%puede que halla errores por causa de la implementacion de clausular y sus derivadas.
    lista([Z],R),%trasforma la forma clausular y luego crea los conjuntos.
    R==Y.% compara

%ejercicio3
colisionan(C,Rc,Z):−
findall(X,(member(X,Rc),
        member(L1,X),
        member(L2,C),
        compl(L1,L2)),CCs),
pares(C,CCs,Z).

crp(X,Y,par(X,Y)).
pares(X,Y,Z):−
      maplist(crp(X),Y,Z).

resolucion(,[]).
resolucion(Cls,[par(C1,C2)|Ps]):−
  member(?,?),
  member(?,?),
  compl(?,?),
  delete(?,?,?),
  delete(?,?,?),
  append(?,?,Res),
  (Res=[]−>false
  ;colisionan(?,?,?),
    append(?,?,?),
      resolucion(?,?)).

inicial([],[]).
inicial([C|Cls],CCls):−
  colisionan(C,Cls,?),
  inicial(?,?),
  append(?,?,?).

satisfacible(Cls):−
  inicial(Cls,In),
  resolucion(Cls,In).
