<!DOCTYPE html>
<html>
<head>
<title>${myapp.title}</title>
<meta charset="utf8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/bootstrap-responsive.css" rel="stylesheet">
<link href="static/bootstrap.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function createJsonObject() {
		var json = {
					"Id":'100', 
					"Gender": $("#gender :selected").text(), 
					"Language": $("#language :selected").text(), 
		            "Latitude": $("#lat").val(), 
		            "Longitude": $("#lon").val(), 
		            "Race": $("#race :selected").text(),
		            "Income": $("#income").val(), 
		            "Psy_Symptoms": $("#psy_symptoms :selected").text(), 
		            "Phy_Symptoms": $("#phy_symptoms :selected").text(),
		            "Other_Symptoms": $("#other_symptoms :selected").text(), 
		            "Doctor": 'Rey', 
		            "Rating":"0"
		            };
		return json;
	}
	function sendRequest() {
		var json = createJsonObject();
		$.ajax({
		    type: 'POST',
		    data: json,
			success: function(data){
		       	alert("Based on users similar to you, we recommend doctor " + data);
		    },
		});
	} 
</script>
<style type="text/css">
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}

.preview {
	float: left;
	margin-right: 20px;
}

.preview .thumb {
	border: 0 none;
	margin-top: 5px;
	width: 252px;
}

/* Custom container */
.container-narrow {
	margin: 0 auto;
	max-width: 900px;
	border-style: solid;
	border-color: transparent;
	background-color: #D8D8D8;
	z-index: 9;
	height: 100%;
	-moz-border-radius: 15px;
	border-radius: 15px;
}

.container-narrow>hr {
	margin: 30px 0;
}

.sidebar-nav {
	padding: 20px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">

				<a class="brand pull-left" href="/home"><em>ZocDoc: Find the Best Doctor for You</em></a>

				<div class="nav-collapse collapse"></div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- end of div for nav bar-->

	<div class="container">
		<!-- <table class="table table-hover">
<tr> -->
		<div class="hero-unit">
			<div>
				<h2 class="text-center">
					<em>Enter Your Info to Get a Recommendation</em>
				</h2>
			</div>
			<form>
				<label for="gender">Gender</label>
				<select id="gender">
					<option>Female</option>
					<option>Male</option>
				</select> 
				<label for="language">Language</label>
				<select id="language">
					<option>Mandarin</option>
					<option>Spanish</option>
					<option>English</option>
					<option>Japanese</option>
					<option>French</option>
					<option>Klingon</option>
					<option>Money</option>
				</select>
				<label for="job">Occupation</label>
				<input type="text" id="job"><br>
				<label for="lat">Latitude</label>
				<input type="text" id="lat"><br>
				<label for="lon">Longitude</label>
				<input type="text" id="lon"><br>
				<label for="race">Race</label>
				<select id="race">
					<option>Black</option>
					<option>Asian</option>
					<option>Latino</option>
					<option>White</option>
					<option>Native_American</option>
					<option>Ugly</option>
				</select> 
				<label for="income">Income</label>
				<input type="text" id="income"><br>
				<label for="psy_symptoms">Pyschological Symptoms</label>
				<select id="psy_symptoms">
					<option>Depression</option>
					<option>Anxiety</option>
					<option>Pyschosis</option>
				</select>
				<label for="psy_symptoms">Physical Symptoms</label>
				<select id="phy_symptoms">
					<option>Headache</option>
					<option>Nausea</option>
					<option>Aching</option>
					<option>Overheating</option>
					<option>Pain</option>
				</select>
				<label for="other_symptoms">Other Symptoms</label>
				<select id="other_symptoms">
					<option>Bleeding</option>
					<option>Rash</option>
					<option>Bruise</option>
				</select><br>
				<br> <input type="button" onclick="sendRequest()"
					value="Submit"> 
			</form>
</body>
</html>
