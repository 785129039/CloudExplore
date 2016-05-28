package com.sunsoft.study.zookeeper;



/**
 * Author: Wangwei
 * Date: 2016/5/24
 * Desc:
 */
public class Application {
    private ZkData zkData;

    private ZkData getUserServiceConfig() {
        //�״λ�ȡʱ������zkȡ�����ã����������ñ仯
        Zk zk = new Zk();
        zkData = zk.readData(Zk.USERSERVICE_CONFIG_PATH);
        System.out.println("config => " + zkData.getDataString());
        return zkData;
    }

    /**
     * ģ���������
     *
     * @throws InterruptedException
     */
    public void run() throws InterruptedException {
        getUserServiceConfig();
        connectionDubboService();
    }

    private void connectionDubboService() throws InterruptedException {
        System.out.println("��׼����������...��");
        System.out.println(zkData.getDataString());
        Thread.sleep(500);
        System.out.println("���ӳɹ�...");
    }

    public void loadFromDB() {
        UserServiceConfig config = new UserServiceConfig(1801, "xxx.xx.xx.xx");
        zkData = new ZkData(config.toString().getBytes(), null);
    }

    public void updateConfigToZk() {
        Zk zk = new Zk();
        if (!zk.existsNode(Zk.USERSERVICE_CONFIG_PATH)) {
            zk.createPath(Zk.USERSERVICE_CONFIG_PATH, zkData);
        } else {
            zk.writeData(Zk.USERSERVICE_CONFIG_PATH, zkData);
        }
    }
}
