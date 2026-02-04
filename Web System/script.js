/* Programmer: Isaiah Godfrey C. Vesina [25-1465-631] */

const rawCSV = `73900438,Osbourne Wakensha,78
115000000,Albie Gierardi,97
112000000,Eleen Pentony,16
840000084,Arie Okenden,99
272000000,Alica Muckley,95`;

let students = rawCSV.split('\n').map(row => {
    const [id, name, grade] = row.split(',');
    return { id, name, grade };
});

function render() {
    const tbody = document.querySelector("#recordTable tbody");
    tbody.innerHTML = students.map((s, index) => `
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td><strong>${s.grade}</strong></td>
            <td><button class="btn-del" onclick="deleteStudent(${index})">Delete</button></td>
        </tr>
    `).join('');
}

function addStudent() {
    const id = document.getElementById('idIn').value;
    const name = document.getElementById('nameIn').value;
    const grade = document.getElementById('gradeIn').value;
    if(id && name) {
        students.push({ id, name, grade });
        render();
        document.getElementById('idIn').value = '';
        document.getElementById('nameIn').value = '';
        document.getElementById('gradeIn').value = '';
    }
}

function deleteStudent(index) {
    students.splice(index, 1);
    render();
}

render();