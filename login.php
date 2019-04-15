<?php
//header("content-type:text/html; charset=utf-8");
$conn = new mysqli('localhost','root','root','hackathon');
$monitor0=$_POST['monitor'];
$message['id']=$_POST['id'];
$message['password']=$_POST['password'];

//账号格式是否正确
if(ctype_digit($message['id']))
{
            $x = 1;//账号是否存在
            $sql0 = sprintf("select count(*) from student where id = '".$message['id']."'");
            $result0 = mysqli_query($conn,$sql0);
            $num = mysqli_fetch_array($result0,MYSQLI_NUM);
            if($num[0]==0)
            {
                $loginjudge = 0;//0账号不存在1密码错误2学生登录成功3班长登录成功4账号格式错误
                echo json_encode($loginjudge);
                $x = 0;
            }

            if($x==1 && $monitor0==0)//账号存在,学生
            {
                $y = 1;
                $sql1 = sprintf("select password from student where id = '".$message['id']."'");//比对密码是否正确
                $result1 = mysqli_query($conn,$sql1);
                $s = md5($message['password']);
                $pass = mysqli_fetch_array($result1,MYSQLI_NUM);
                if($pass[0]!=$s)
                {
                    $loginjudge = 1;
                    echo json_encode($loginjudge);
                    $y = 0;
                }
                if($y)//密码正确
                {
                    $loginjudge = 2;
                    echo json_encode($loginjudge);
                }
            }
            if($x==1 && $monitor==1)//账号存在，班长
            {
                $y = 1;
                $sql1 = sprintf("select password from monitor where id = '".$message['id']."'");//比对密码是否正确
                $result1 = mysqli_query($conn,$sql1);
                $s = md5($message['password1']);
                $pass = mysqli_fetch_array($result1,MYSQLI_NUM);
                if($pass[0]!=$s)
                {
                    $loginjudge = 1;
                    echo json_encode($loginjudge);
                    $y = 0;
                }
                if($y)//密码正确
                {
                    $loginjudge = 3;
                    echo json_encode($loginjudge);
                }
            }
}
else
{
            $loginjudge = 4;//账号格式错误
            echo json_encode($loginjudge);
}
mysqli_close($conn);
?>