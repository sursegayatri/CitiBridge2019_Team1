//For file format validations
function fileupload() {
	var fileUpload = document.getElementById("fileUpload");
	// alert("Uploading Invalid Transactions ....");
	var head = new Array("Ref#", "Date", "Payer|Account", "Payee|Account",
			"Amount", "Feed Status");
	var regex = /^([A-Z])?([a-z0-9\s_\\.:])+(.txt)$/;
	if (regex.test(fileUpload.value.toLowerCase())) {
		if (typeof (FileReader) != "undefined") {
			var reader = new FileReader();
			reader.onload = function(e) {
				var table = document.createElement("table");
				var rows = e.target.result.split("\n");

				if (rows.length == 1) {
					alert("File is Empty!");
					return false;
				}

				var row = table.insertRow(-1);
				for (var j = 0; j < 6; j++) {
					var headercell = document.createElement("TH");
					headercell.innerHTML = head[j];
					row.appendChild(headercell);

				}

				for (var i = 0; i < rows.length; i++) {
					row = table.insertRow(-1);

					var cells = rows[i].split(" ");
					for (var j = 0; j < cells.length; j++) {

						var cell = row.insertCell(-1);
						cell.innerHTML = cells[j];
					}

				}
				var dvCSV = document.getElementById("dvCSV");
				dvCSV.innerHTML = "";
				dvCSV.appendChild(table);
			}
			reader.readAsText(fileUpload.files[0]);
		} else {
			alert("This browser does not support HTML5.");
		}
	} else {
		alert("Please upload a valid  file.");
	}
}
