package com.codeup.blogapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{addendX}/and/{addendY}", method = RequestMethod.GET)
    @ResponseBody
    public Long addXPlusY (@PathVariable long addendX, @PathVariable long addendY){
        long sum = addendX + addendY;
        return sum;
    }

    @RequestMapping(path = "/subtract/{subtrahend}/from/{minuend}", method = RequestMethod.GET)
    @ResponseBody
    public Long subtractXFromY (@PathVariable long subtrahend, @PathVariable long minuend){
        long difference = minuend - subtrahend;
        return difference;
    }

    @RequestMapping(path = "/multiply/{factorX}/and/{factorY}", method = RequestMethod.GET)
    @ResponseBody
    public Long multiplyXAndY (@PathVariable long factorX, @PathVariable long factorY){
        long product = factorX * factorY;
        return product;
    }

    @RequestMapping(path = "/divide/{dividend}/by/{divisor}", method = RequestMethod.GET)
    @ResponseBody
    public Long divideXByY (@PathVariable long dividend, @PathVariable long divisor){
        long quotient = dividend / divisor;
        return quotient;
    }
}
