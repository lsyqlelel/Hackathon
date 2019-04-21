<?php
header("content-type:text/html; charset=utf-8");
$conn = new mysqli('localhost','root','root','hackathon');

$monitor0 = $_POST['monitor'];
$message['username'] = $_POST['username'];
$message['password'] = $_POST['password'];
$response=array();

//账号格式是否正确
if(ctype_digit($message['username']))//$message['username']为纯数字（必须是字符串类型），合法
{
    if($monitor0 == "0")//学生
    {
        $x1 = 1;
        $sql0 = sprintf("select count(*) from t_students where id = '".$message['username']."'");
        $result0 = mysqli_query($conn,$sql0);
        $num = mysqli_fetch_array($result0,MYSQLI_NUM);
        if($num[0] == 0)//学生账号不存在
        {
            $response["success"] = 0;//0账号不存在1密码错误2学生登录成功3班长登录成功4账号格式错误5无班长权限
            $x1 = 0;
        }
        if($x1)//学生账号存在
        {
            $y1 = 1;
            $sql1 = sprintf("select password from t_students where id = '".$message['username']."'");
            $result1 = mysqli_query($conn,$sql1);
            $s = $message['password'];
            $pass = mysqli_fetch_array($result1,MYSQLI_NUM);
            if($pass[0] != $s)//密码错误
            {
                $response["success"] = 1;
                $y1 = 0;
            }
            if($y1)//密码正确，学生登录成功
            {
                $response["success"] = 2;
            }
        }
    }
    if($monitor0 == "1")//班长
    {
        $x1 = 1;
        $sql0 = sprintf("select count(*) from monitor where id = '".$message['username']."'");
        $result0 = mysqli_query($conn,$sql0);
        $num = mysqli_fetch_array($result0,MYSQLI_NUM);
        if($num[0] == 0)//班长账号不存在
        {
            $response["success"] = 5;
            $x1 = 0;
        }
        if($x1)//班长账号存在
        {
            $y1 = 1;
            $sql1 = sprintf("select password from monitor where id = '".$message['username']."'");
            $result1 = mysqli_query($conn,$sql1);
            $s = $message['password'];
            $pass = mysqli_fetch_array($result1,MYSQLI_NUM);
            if($pass[0] != $s)//密码错误
            {
                $response["success"] = 1;
                $y1 = 0;
            }
            if($y1)//密码正确，班长登录成功
            {
                $response["success"] = 3;
            }
        }
    }
}
else
{
    $response["success"]= 4;//账号格式错误
}

echo json_encode($response);

ini_set("error_reporting","E_ALL & ~E_NOTICE");
mysqli_close($conn);
?>