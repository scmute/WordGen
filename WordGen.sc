WordGen{

	var file;

	*new { arg path;
		var obj;

		obj = super.new;
		obj.init(path);

		^obj
	}

	init{ arg path;
		file = FileReader.read(path, true).postcs;
	}

	next{
		var a = "", b = "", noExit = true, i = 0, j = 0;

		// obtiene una palabra aleatoriamente en el texto
		{a.size < 3}.while{
			a = file[file.size.rand][file[0].size.rand];
		};
		
		// toma las tres primeras letras
		a = a[0] ++ a[1] ++ a[2];

		// recorre todo el texto
		while({i < file.size}, {		
			j = 0;
			file[i].size.do{
				b = file[i][j];
				// busca una palabra que contenga las dos últimas letras de "a"
				if(b.contains(a[a.size-2] ++ a[a.size-1]) && exit, {
					// si la encuentra toma el siguiente caracter
					b = b[b.findAll(a[a.size-2] ++ a[a.size-1])[0]+2];
					if(WordGen.isValid(b), {
						a = a ++ b;
					},
					{
						noExit = false;
					});
				});
				j = j + 1;
			};
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
