WordGen{

	var file;

	*new { arg path;
		var obj;

		obj = super.new;
		obj.init(path);

		^obj
	}

	init{ arg path;
		// postcs crea un array de palabras
		file = FileReader.read(path, true).postcs;
	}

	next{
		var a = "", b = "", exit = false, i = 0, j = 0;

		// obtiene una palabra aleatoriamente en el texto
		{a.size < 3}.while{
			a = file[file.size.rand][file[0].size.rand];
		};
		
		// toma las tres primeras letras
		a = a[0] ++ a[1] ++ a[2];

		// continua buscando a partir de la palabra encontrada
		while({i < file.size && not(exit)}, {
			while({j < file[i].size && not(exit)}, {
				b = file[i][j];
				// busca una palabra que contenga las dos últimas letras de "a"
				if(b.contains(a[a.size-2] ++ a[a.size-1]), {
					// si la encuentra toma el siguiente caracter
					b = b[b.findAll(a[a.size-2] ++ a[a.size-1])[0]+2];
					if(WordGen.isValid(b), {
						a = a ++ b;
					},
					{
						exit = true;
					});
				});
				j = j + 1;
			});
			j = 0;
			i = i + 1;
		});	

		^a;
	}

	*isValid{arg car;
		var alfb = "abcdefghijklmnopqrstuvwxyzáéíóú";

		if(car.notNil && alfb.contains(car.asString),  {^true});

		^false;
	}

}
