FaustHihat : MultiOutUGen
{
  *ar { | frequency(440.0), gain(0.3), gate(0.0) |
      ^this.multiNew('audio', frequency, gain, gate)
  }

  *kr { | frequency(440.0), gain(0.3), gate(0.0) |
      ^this.multiNew('control', frequency, gain, gate)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustHihat" }


  info { ^"Generated with Faust" }
}

