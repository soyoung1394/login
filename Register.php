<?php
$con=mysqli_connect("localhost","syoung1394","donghang123","syoung1394");

$userID = $_POST["userID"];
$userPassword = $_POST["userPassword"];
$userName = $_POST["userName"];
$userPhone = $_POST["userPhone"];
$userPhoto = $_POST["userPhoto"];

$statement = mysqli_prepare($con, "INSERT INTO USER VALUES(?,?,?,?,?)");
mysqli_stmt_bind_param($statement,"sssss", $userID, $userPassword, $userName, $userPhone, $userPhoto);
mysqli_stmt_execute($statement);

$response=array();
$response["success"]=true;

echo json_encode($response);
?>
