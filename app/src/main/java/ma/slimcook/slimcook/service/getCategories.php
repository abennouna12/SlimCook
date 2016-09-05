<?php 

    header('Access-Control-Max-Age: 86400');    // cache for 1 day
    header('Access-Control-Allow-Origin: *' );
    header('Access-Control-Allow-Credentials: true' );
    header('Access-Control-Request-Method: *');
    header('Access-Control-Allow-Methods: POST, GET, PUT, DELETE,     OPTIONS');
    header('Access-Control-Allow-Headers: *,x-requested-with,Content-Type');
    header('Content-Type: application/json');
    
    require_once 'db.php'; // The mysql database connection script
    
    $sql = "SELECT  `Name` ,  `Description` ,  `image` , `id`";
    $sql = $sql . " FROM  categories_category";
    $sql = $sql . " ORDER BY ordre ASC";

    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    $emparray = array();

    while($row = mysqli_fetch_assoc($result))
    {
        
        $row['image'] = ($row['image']=="") ? "images/nopicture.jpg" : $row['image'];
        $temparray = array('Name' => utf8_encode($row['Name']), 'Description' => utf8_encode($row['Description']), 'image' => utf8_encode($row['image']),'id' => utf8_encode($row['id']));
        $emparray[] = $temparray;
    }

    echo json_encode($emparray);
?>


