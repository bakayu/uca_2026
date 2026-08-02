const fields = ["First Name", "Last Name", "Email"];

function generateForm(fieldsData) {
  return fieldsData
    .map((field) => `  <label>${field}</label>\n  <input type="text">`)
    .join("\n");
}

document.getElementById("form-container").innerHTML = generateForm(fields);
