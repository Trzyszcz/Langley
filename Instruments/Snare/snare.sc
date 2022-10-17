FaustSnare : MultiOutUGen
{
  *ar { | rightpush(0.0), frequency(220.0), gain(1.0), gate(0.0) |
      ^this.multiNew('audio', rightpush, frequency, gain, gate)
  }

  *kr { | rightpush(0.0), frequency(220.0), gain(1.0), gate(0.0) |
      ^this.multiNew('control', rightpush, frequency, gain, gate)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustSnare" }


  info { ^"Generated with Faust" }
}

