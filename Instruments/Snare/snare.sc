FaustSnare : MultiOutUGen
{
  *ar { | frequency(220.0), gain(1.0), gate(0.0) |
      ^this.multiNew('audio', frequency, gain, gate)
  }

  *kr { | frequency(220.0), gain(1.0), gate(0.0) |
      ^this.multiNew('control', frequency, gain, gate)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustSnare" }


  info { ^"Generated with Faust" }
}

