package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVO;
import com.yc.tx.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-27 18:59
 */
@Controller
@Slf4j
@Api(description = "银行账户操作接口",tags = {"操作接口","控制层"})
public class AccountsController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/openAccounts",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "开户",notes = "输入金额开户")
    @ApiImplicitParams(value = {@ApiImplicitParam (name = "money",required = true,dataType = "double"),
//            @ApiImplicitParam (name = "accountid",dataType = "java.lang.double")
    })
    public @ResponseBody
    ResultVo openAccounts(AccountVO accountVO){
        log.debug("用户请求开户，存入"+accountVO.getMoney());
        ResultVo rv=new ResultVo();
       try {
           Accounts a = new Accounts();

           double money = 1;
           if (accountVO.getMoney() != null ) {
               money = accountVO.getMoney();
           }
           Integer id = accountService.openAccount(a, money);
           a.setAccountId(id);
           a.setBalance(money);
           rv.setCode(1);
           rv.setData(a);
       }catch (Exception ex){
           ex.printStackTrace();
           rv.setCode(0);
           rv.setData("开户金额不能为负");
           rv.setMsg("请重新输入开户金额");
       }
        return rv;
    }


    @RequestMapping(value = "/deposit",method = RequestMethod.POST)
    @ApiOperation(value = "存款",notes = "根据账号，存款金额发出存款操作，返回操作完成后新的余额")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountid",value = "存款帐号",required = true,dataType = "int"),@ApiImplicitParam(name = "money",value = "存款金额",required = true,dataType = "Double")})
    public @ResponseBody
    ResultVo<Accounts>deposit(AccountVO accountVO){
        ResultVo<Accounts> rv=new ResultVo();
        Accounts a =new Accounts();
        a.setAccountId(accountVO.getAccountid());
        try{
            a=accountService.deposite(a,accountVO.getMoney(), OpTypes.deposite.getName(),"");
            rv.setCode(1);
            rv.setData(a);



        }catch (Exception e){
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;

    }
    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    @ApiOperation(value = "取款",notes = "输入取款账户")
    @ApiImplicitParams({@ApiImplicitParam(name = "accountid",value = "取款账户",required = true,dataType = "int"),
            @ApiImplicitParam(name = "money",value = "取款金额",required = true,dataType = "Double")})
    public @ResponseBody
    ResultVo<Accounts>withdraw(AccountVO accountVO){
        ResultVo<Accounts> rv=new ResultVo();
        Accounts a=new Accounts();
        a.setAccountId(accountVO.getAccountid());
       try{
           a=accountService.withdraw(a,accountVO.getMoney(),OpTypes.withdraw.getName(),"");
           rv.setCode(1);
           rv.setData(a);
       }catch (Exception e){
           e.printStackTrace();
           rv.setCode(0);
           rv.setMsg(e.getMessage());
       }
       return rv;
    }
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    @ApiOperation(value = "转账",notes = "输入取款账户，对方账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountid",value = "自己账户",required = true,dataType = "int"),
            @ApiImplicitParam(name = "inAccountId",value = "对方账号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "money",value = "取款金额",required = true,dataType = "Double")})
    public @ResponseBody
    ResultVo<Accounts>transfer(AccountVO accountVO){
        Accounts inaccount=new Accounts();
        inaccount.setAccountId(accountVO.getInAccountId());
        Accounts outaccount=new Accounts();
        outaccount.setAccountId(accountVO.getAccountid());
        ResultVo<Accounts> rv=new ResultVo();


        try{
            Accounts a=accountService.transfer(inaccount,outaccount,accountVO.getMoney());
            rv.setCode(1);
            rv.setData(a);
        }catch (Exception e){
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;

    }

    @RequestMapping(value = "/query",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value="查询余额",notes = "根据账号来完成查询")
    @ApiImplicitParam(name = "accountid",value = "账户",required = true,dataType = "int")
    public @ResponseBody
    ResultVo<Accounts>query(AccountVO accountVO){
        ResultVo<Accounts> rv=new ResultVo<>();
        Accounts a=new Accounts();
        try {
            a.setAccountId(accountVO.getAccountid());
            a=accountService.showBalance(a);
            rv.setCode(1);
            rv.setData(a);
        }catch (Exception e){
            e.printStackTrace();
            rv.setCode(0);
            rv.setMsg(e.getMessage());
        }
        return rv;

    }


}
