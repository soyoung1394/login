<?php
$con=mysqli_connect("localhost","syoung1394","donghang123","syoung1394");

$userID = $_POST["userID"];
$userPassword = $_POST["userPassword"];

$statement = mysqli_prepare($con, "SELECT * FROM HELPER WHERE userID = ? AND userPassword = ?");
mysqli_stmt_bind_param($statement,"ss", $userID, $userPassword);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$userID, $userPassword, $userName, $userPhone);

$response = array();
$response["success"] = false;

while(mysqli_stmt_fetch($statement)){
  $response["success"] = true;
  $response["userID"] = $userID;
  $response["userPassword"] = $userPassword;
  $response["userName"] = $userName;
  $response["userPhone"] = $userPhone;
}
echo json_encode($response);
?>
