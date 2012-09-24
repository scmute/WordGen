WordGen.sc
==========

Implementación en Supercollider del algoritmo de Hilario Fernández Long para generar palabras aleatorias (jitanjáforas).

El siguiente ejemplo genera 5 palabras aleatorias utilizando la clase WordGen:

   a = WordGen.new("texto.txt");

   5.do{
      a.next.postln;
   }
