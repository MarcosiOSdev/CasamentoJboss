insert into usuario(id_usuario,login , oauth, permissao, senha) values (1,'lipe', 'aah123dashfa', 'USUARIO', '123');
insert into usuario(id_usuario,login , oauth, permissao, senha, casal) values (2,'tai', 'aattas11t64hfa', 'USUARIO', '123',1);
update usuario set casal=2 where id=1;
