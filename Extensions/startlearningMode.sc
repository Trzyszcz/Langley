+ Langley {
	*startlearningMode {

		Langley.setlearningMode(true);

		~date = Date.getDate;
		~memorylist = List.newClear;

		Routine {
			var i = 0;
			while(
				{ Langley.learningMode },
				{
					var d = Date.getDate;
					~hihat.set(\gate, 1);
					~memorylist.add("Tik" + d.hourStamp ++ "\n");
					0.05.wait;
					~hihat.set(\gate, 0);
					//"tik".postln;
					(60/Langley.tempo - 0.05).wait;
					i = i + 1;
				}
			);

			~path = "./Documents/supercollider/Langley/data/" ++ ~date.stamp();
			~file = File.new(~path, "w");
			~file.write("dupa\n");
			~memorylist.do( { |item| ~file.write(item) } );
			~file.close;
			"Closed".postln;
			~memorylist.postln;
		}.play;

	}
}