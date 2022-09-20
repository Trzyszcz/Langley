FaustBassdrum : MultiOutUGen
{
  *ar { | attack(0.001), decay(0.1), gain(1.0), gate(0.0) |
      ^this.multiNew('audio', attack, decay, gain, gate)
  }

  *kr { | attack(0.001), decay(0.1), gain(1.0), gate(0.0) |
      ^this.multiNew('control', attack, decay, gain, gate)
  } 

  init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(2, rate)
  }

  name { ^"FaustBassdrum" }


  info { ^"Generated with Faust" }
}

