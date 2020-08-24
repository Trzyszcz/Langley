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
					~memorylist.add("Tik" ++ (i+1).asString + d.hourStamp ++ "\n");
					(i+1).postln;
					0.05.wait;
					~hihat.set(\gate, 0);
					//"tik".postln;
					(60/Langley.tempo - 0.05).wait;
					i = i + 1;
					i = i % Langley.meter;
				}
			);

			~path = "./Documents/supercollider/Langley/data/" ++ ~date.stamp();
			~file = File.new(~path, "w");
			~file.write(Langley.tempo.asString + Langley.meter.asString ++ "/4");
			~file.write("\n");
			~memorylist.do( { |item| ~file.write(item) } );
			~file.close;
			"Closed".postln;
			~memorylist.postln;
			~memorylist.clear;
		}.play;

	}
}