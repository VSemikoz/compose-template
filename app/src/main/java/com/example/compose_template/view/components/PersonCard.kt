package com.example.compose_template.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_template.view.model.PersonGender
import com.example.compose_template.view.model.PersonItemUi
import com.example.compose_template.view.theme.TemplateColors
import com.example.compose_template.view.theme.TemplateTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    item: PersonItemUi,
) {
    val dateString = item.birthDate.format(DateTimeFormatter.ISO_DATE)
    val backgroundColor = when (item.gender) {
        PersonGender.male -> TemplateColors.Blue
        PersonGender.female -> TemplateColors.Pink
    }
    Card(
        modifier
            .wrapContentSize()
            .height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Row {
            AsyncImage(
                model = item.imageUrl, contentDescription = "person_image",
                Modifier.weight(1f),
                contentScale = ContentScale.FillWidth,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(2f)) {
                Text(text = item.fullName)
                Text(text = item.location)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = dateString, Modifier.align(Alignment.End))
            }

        }
    }
}


@Preview
@Composable
private fun PersonCardPreview() {
    TemplateTheme {
        Column {
            PersonCard(item = personMaleMock)
            PersonCard(item = personFemaleMock)
        }
    }
}


private val personMaleMock = PersonItemUi(
    fullName = "Male TestTest TestTestTest",
    birthDate = LocalDate.now(),
    imageUrl = "https://randomuser.me/api/portraits/men/75.jpg",
    gender = PersonGender.male,
    location = "United States Billings Michigan Valwood Pkwy 8844",
)

private val personFemaleMock = PersonItemUi(
    fullName = "Female TestTest TestTestTest",
    birthDate = LocalDate.now(),
    imageUrl = "https://randomuser.me/api/portraits/women/75.jpg",
    gender = PersonGender.female,
    location = "United States Billings Michigan Valwood Pkwy 8844",
)