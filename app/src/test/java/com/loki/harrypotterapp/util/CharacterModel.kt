package com.loki.harrypotterapp.util

import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.models.Wand

val model = CharacterItem(
    id ="9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
    name ="Harry Potter",
    alternate_names = listOf(
        "The Boy Who Lived",
        "The Chosen One"
    ),
    species = "human",
    gender ="male",
    house = "Gryffindor",
    dateOfBirth ="31-07-1980",
    yearOfBirth = 1980,
    wizard =true,
    ancestry = "half-blood",
    eyeColour = "green",
    hairColour = "black",
    wand = Wand (
            wood =  "holly",
            core = "phoenix feather",
            length = 11.0
    ),
    patronus = "stag",
    hogwartsStudent = true,
    hogwartsStaff = false,
    actor = "Daniel Radcliffe",
    alternate_actors = listOf(),
    alive = true,
    image ="https://ik.imagekit.io/hpapi/harry.jpg"
)

val model2 = CharacterItem(
    id ="c3b1f9a5-b87b-48bf-b00d-95b093ea6390",
    name ="Ron Weasley",
    alternate_names = listOf(
        "Dragomir Despard",
    ),
    species = "human",
    gender ="male",
    house = "Gryffindor",
    dateOfBirth ="01-03-1980",
    yearOfBirth = 1980,
    wizard =true,
    ancestry = "pure-blood",
    eyeColour = "blue",
    hairColour = "red",
    wand = Wand (
        wood =  "willow",
        core = "unicorn tail-hair",
        length = 14.0
    ),
    patronus = "Jack Russell terrier",
    hogwartsStudent = true,
    hogwartsStaff = false,
    actor = "Rupert Grint",
    alternate_actors = listOf(),
    alive = true,
    image ="https://ik.imagekit.io/hpapi/ron.jpg"
)