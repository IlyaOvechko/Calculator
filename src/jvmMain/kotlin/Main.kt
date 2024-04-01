import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.*
import java.io.File



@Composable
@Preview
fun App(winState: WindowState) {
    var number1: String by remember { mutableStateOf("") }
    var number2: String by remember { mutableStateOf("") }
    var text: String by remember { mutableStateOf("") }
    var result: Int by remember { mutableStateOf(0) }
    val regex = Regex("[+\\-*/]")
    val textNum = remember { mutableStateListOf(String()) }
    var textFieldValueState by remember { mutableStateOf(TextFieldValue()) }
    val focusRequester = remember { FocusRequester() }
    var isHistoryShown by remember { mutableStateOf(false) }
    val file = File("calculate.txt")
    val history = file.readLines()
    file.writeText("")



    MaterialTheme {
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.width(235.dp).padding(2.dp)) {
                OutlinedTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    value = textFieldValueState,
                    onValueChange = {
                        text = it.text.filterNot { it -> it.isLetter() }
                        textFieldValueState = TextFieldValue(text, selection = it.selection)
                    }
                )
                Row(Modifier.size(240.dp, 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Button(modifier = Modifier
                        .size(100.dp, 40.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            for (te in textFieldValueState.text.split(regex, 2)) {
                                textNum.add(0, te.filterNot { it in "$regex" })
                            }
                            number1 = textNum[1]
                            number2 = textNum[0]
                            val textSign = textFieldValueState.text.filterNot { it in "1234567890" }.last()
                            result = when (textSign) {
                                '+' -> number1.toInt() + number2.toInt()
                                '-' -> number1.toInt() - number2.toInt()
                                '*' -> number1.toInt() * number2.toInt()
                                '/' -> number1.toInt() / number2.toInt()
                                else -> 0
                            }
                            file.appendText(" ${textFieldValueState.text}=$result\n")
                        }) {
                        Text("=")
                    }
                    Button(modifier = Modifier
                        .size(70.dp, 40.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start).dropLast(1) +
                                    textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start - 1))
                        }) {
                        Text("<=")
                    }
                    Button(modifier = Modifier
                        .size(70.dp, 40.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.dropLast(textFieldValueState.text.length))
                        }) {
                        Text("Cn")
                    }
                }
                Row(Modifier.size(240.dp, 60.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "1"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("1")
                    }
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "2"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("2")
                    }
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "3"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("3")
                    }
                    Button(modifier = Modifier
                        .size(50.dp, 60.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "+"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("+")
                        }
                }
                Row(Modifier.size(240.dp, 60.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "4"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("4")
                    }
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "5"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("5")
                    }
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "6"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("6")
                    }
                    Button(modifier = Modifier
                        .size(50.dp, 60.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "-"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("-")
                    }
                }
                Row(Modifier.size(240.dp, 60.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "7"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("7")
                    }
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "8"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("8")
                    }
                    Button(modifier = Modifier
                    .size(60.dp, 60.dp)
                    .padding(2.dp),
                    shape = CircleShape,
                    onClick = { focusRequester.requestFocus()
                        textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "9"
                                + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                    }) {
                    Text("9")
                    }
                    Button(modifier = Modifier
                        .size(50.dp, 60.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "*"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("*")
                    }
                }
                Row(Modifier.size(240.dp, 60.dp).padding(60.dp, 0.dp, 0.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Button(modifier = Modifier
                        .size(60.dp, 60.dp)
                        .padding(2.dp),
                        shape = CircleShape,
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "0"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("0")
                    }
                    Button(modifier = Modifier
                        .size(50.dp, 60.dp)
                        .padding(2.dp),
                        onClick = { focusRequester.requestFocus()
                            textFieldValueState = TextFieldValue(textFieldValueState.text.substring(0, textFieldValueState.selection.start) + "/"
                                    + textFieldValueState.text.substring(textFieldValueState.selection.start), selection = TextRange(textFieldValueState.selection.start + 1))
                        }) {
                        Text("/")
                    }
                }
                Row(Modifier.size(240.dp, 60.dp).padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
//                    var openWindowState = remember { mutableStateOf(false) }
                    TextField(result.toString(),{
                        result.toString()
                    },
                        Modifier.width(130.dp))
                    Button( onClick = { isHistoryShown =! isHistoryShown }) {
                        if (isHistoryShown){
                            Text("Close")
                        } else {
                            Text("History")
                        }
                    }
                }
            }

            Window(
                visible = isHistoryShown,
                onCloseRequest = { isHistoryShown = false},
                title = "History",
                state = WindowState(
                    size =  DpSize(250.dp, 450.dp),
                    position = WindowPosition( (winState.position.x.value + 250).dp, winState.position.y.value.dp))
            ){
                MaterialTheme {
                    Column(Modifier
                        .size(240.dp, 440.dp)
                        .verticalScroll(ScrollState(0))
                    ){
                        val newHistory = file.readLines()
                        for (newHi in newHistory.reversed()) {
                            Text(newHi,
                            Modifier.padding(2.dp))
                        }
                        for (hi in history.reversed()) {
                            Text(hi ,
                                Modifier.padding(2.dp))
                        }
                    }
                }
            }
        }
    }
}


fun main() {
    application {
        val winState = rememberWindowState(
            size = DpSize(250.dp, 450.dp),
            position = WindowPosition(48.dp, 48.dp)
        )
        Window(
            onCloseRequest = ::exitApplication,
            resizable = false,
            title = "Calculator",
            state = winState
        ) {
            App(winState)
        }
    }
}
