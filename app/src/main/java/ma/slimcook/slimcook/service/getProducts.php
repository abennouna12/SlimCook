<?php 

    header('Access-Control-Max-Age: 86400');    // cache for 1 day
    header('Access-Control-Allow-Origin: *' );
    header('Access-Control-Allow-Credentials: true' );
    header('Access-Control-Request-Method: *');
    header('Access-Control-Allow-Methods: POST, GET, PUT, DELETE,     OPTIONS');
    header('Access-Control-Allow-Headers: *,x-requested-with,Content-Type');
    header('Content-Type: application/json');
    
    require_once 'db.php'; // The mysql database connection script
    
    $sql = " SELECT DISTINCT `Name` , `Prix` ,  `Description` ,  `main_image` ,  P.id FROM  `products_product` AS P";
    $sql = $sql . " LEFT JOIN products_product_categories AS C ON C.product_id = P.id";
    $sql = $sql . " WHERE P.masquer = 0";
    $sql = ($_GET['id']!="0") ? $sql . " AND C.category_id=" . $_GET['id'] : $sql;

    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));
    $emparray = array();

    while($row = mysqli_fetch_assoc($result))
    {
       
        $sql2 = " SELECT V.Prix,V.Name, V.id ";
        $sql2 = $sql2 . " FROM  `products_variant` AS V ";
        $sql2 = $sql2 . " LEFT JOIN  `products_product` AS P ON V.`produit_id` = P.`id` ";
        $sql2 = $sql2 . " WHERE P.id = " . $row['id'];

        $result2 = mysqli_query($connection, $sql2) or die("Error in Selecting " . mysqli_error($connection));
        $emparray2 = array();

        $row_cnt2 = mysqli_num_rows($result2);
        
        if($row_cnt2 > 0)
        {
            while($row2 = mysqli_fetch_assoc($result2))
            {
                $temparray2 = array('id' => utf8_encode($row2['id']), 'Prix' => utf8_encode($row2['Prix']), 'Name' => utf8_encode($row2['Name']));
                $emparray2[] = $temparray2;
            } 
        }
        else
        {
            $temparray2 = array('id' => utf8_encode($row2['id']), 'Prix' => utf8_encode($row['Prix']), 'Name' => "");
            $emparray2[] = $temparray2;
        }

        $row['main_image'] = ($row['main_image']=="") ? "images/nopicture.jpg" : $row['main_image'];

        $row['main_image'] = "http://slimcook.ma/site_media/" . $row['main_image'];
        
        $temparray = array('Id' => utf8_encode($row['id']), 'Name' => utf8_encode($row['Name']), 'Variante' => $emparray2 , 'Description' => utf8_encode($row['Description']), 'main_image' => utf8_encode($row['main_image']));
        $emparray[] = $temparray;
    }

    echo json_encode($emparray);
?>

