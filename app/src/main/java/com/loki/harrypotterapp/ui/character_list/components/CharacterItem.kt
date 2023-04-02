package com.loki.harrypotterapp.ui.character_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.loki.harrypotterapp.domain.models.CharacterItem

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    characterItem: CharacterItem,
    onItemClick: (CharacterItem) -> Unit = {}
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(characterItem) }
            .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(4.dp))
    ) {

        Row (
            horizontalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.size(150.dp) ) {
                Image(
                    painter = rememberImagePainter(data = characterItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = characterItem.name,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                if (characterItem.ancestry.isNotEmpty()) {
                    Text(
                        text = characterItem.ancestry,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                if (characterItem.actor.isNotEmpty()) {
                    Text(
                        text = characterItem.actor,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {

                    val color = if (characterItem.wizard) MaterialTheme.colors.error else MaterialTheme.colors.onError
                    Chip(
                        onClick = { /*TODO*/ },
                    ) {
                        Text(
                            text = if (characterItem.wizard) "Wizard" else "Not Wizard",
                            color = color
                        )
                    }
                }
            }
        }
    }
}