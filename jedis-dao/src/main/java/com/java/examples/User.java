package com.java.examples;

import redis.clients.jedis.Pipeline;

public class User {
    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private long id;

    public User addUser(User user) {
        long userId = jedis.incr(Keys.USER_IDS.key());
        user.setId(userId);

        //Getting the Pipeline
        Pipeline pipeline = jedis.pipelined();
        //add to users list
        pipeline.lpush(Keys.USER_ALL.key(), String.valueOf(userId));
        //add to the hash

        pipeline.hmset(Keys.USER_DATA.formated(String.valueOf(userId)), BeanUtil.toMap(user));

        pipeline.sync();

        return user;
    }

    public User getUser(long userId) {
        String userInfoKey = Keys.USER_DATA.formated(String.valueOf(userId));
        Map<String, String> properties = jedis.hgetAll(userInfoKey);
        return BeanUtil.populate(properties, new User());
    }

    public boolean remove(User user) {
        String userInfoKey = Keys.USER_DATA.formated(String.valueOf(userId));
        Pipeline pipeline = jedis.pipelined();
        Response<Long> responseDel = pipeline.del(userInfoKey);
        Response<Long> responseLrem = pipeline.lrem(Keys.USER_ALL.key(), 0, String.valueOf(userId));
        pipeline.sync();
        return responseDel.get() > 0 && responseLrem.get() > 0;
    }

    public User update(User user) {
        String userInfoKey = Keys.USER_DATA.formated(String.valueOf(user.getId()));
        jedis.hmset(userInfoKey ,BeanUtil.toMap(user));
        return user;
    }

    public List<User> list(){
        List<User> users = new ArrayList<User>();
        //Get all user ids from the redis list using LRANGE
        List<String> allUserIds = jedis.lrange(Keys.USER_ALL.key(), 0, -1);
        if(allUserIds != null && !allUserIds.isEmpty()){
            List<Response<Map<String,String>>> responseList = new ArrayList<Response<Map<String,String>>>();

            Pipeline pipeline = jedis.pipelined();
            for(String userId : allUserIds){
                //call HGETALL for each user id
                responseList.add(pipeline.hgetAll(Keys.USER_DATA.formated(userId)));
            }
            pipeline.sync();
            //iterate over the pipelined results
            for(Response<Map<String, String>> properties : responseList){
                users.add(BeanUtil.populate(properties.get(), new User()));
            }
        }
        return users;
    }

    public void setId(long id) {
        this.id = id;
    }
}
