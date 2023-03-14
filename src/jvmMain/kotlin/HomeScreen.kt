import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    var text by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()
                PathTextField(value = text, onValueChange = { text = it })
                DuplicatesCheckbox(checked = isChecked, onCheckedChange = { isChecked = it })
                ConvertButton {
                    Converter(text, isChecked).convertContent()
                }
            }
        }
    }
}

@Composable
fun Logo() {
    val painter: Painter = painterResource("drawable/cc_logo.jpg")
    Image(
        painter = painter,
        contentDescription = "My Image",
        modifier = Modifier.size(246.dp).padding(bottom = 44.dp)
    )
}

@Composable
fun PathTextField(
    textSize: TextUnit = 18.sp,
    value: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .onFocusEvent {
                isFocused = it.isFocused
            },
        placeholder = {
            Text(
                "Paste directory path",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
            )
        },
    )
}

@Composable
fun DuplicatesCheckbox(
    textSize: TextUnit = 18.sp,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Remove duplicates", fontSize = textSize)
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Composable
fun ConvertButton(textSize: TextUnit = 18.sp, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp)
    ) {
        Text("Convert", fontSize = textSize)
    }
}