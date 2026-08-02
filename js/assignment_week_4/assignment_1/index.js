const users = [
  { name: "Alice", age: 53 },
  { name: "Tim", age: 74 },
];

function generateTable(usersData) {
  const rows = usersData
    .map(
      (user) =>
        `  <tr>\n    <td>${user.name}</td>\n    <td>${user.age}</td>\n  </tr>`,
    )
    .join("\n");

  return `<table>\n${rows}\n</table>`;
}

document.getElementById("table-container").innerHTML = generateTable(users);
