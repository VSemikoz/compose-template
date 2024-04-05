import com.example.compose_template.domain.entity.person.person.PersonDobEntry
import com.example.compose_template.domain.entity.person.person.PersonIdEntry
import com.example.compose_template.domain.entity.person.person.PersonLocationEntry
import com.example.compose_template.domain.entity.person.person.PersonNameEntry
import com.example.compose_template.domain.entity.person.person.PersonPictureEntry
import com.example.compose_template.domain.entity.person.person.PersonRegisteredEntry

data class PersonEntry(
    val gender: String,
    val name: PersonNameEntry,
    val location: PersonLocationEntry,
    val email: String,
    val registered: PersonRegisteredEntry,
    val dob: PersonDobEntry,
    val phone: String,
    val cell: String,
    val id: PersonIdEntry,
    val picture: PersonPictureEntry,
)
