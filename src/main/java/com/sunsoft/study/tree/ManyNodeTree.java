/*
 * Copyright Walker Studio
 * All Rights Reserved.
 * 
 * �ļ����ƣ� ManyNodeTree.java
 * ժ Ҫ��
 * �� �ߣ� Walker
 * ����ʱ�䣺 2013-03-19
 */
package com.sunsoft.study.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������ɡ���������
 * 
 * @author Walker
 * @version 1.0.0.0
 */
public class ManyNodeTree 
{
	/** ����*/
	private ManyTreeNode root;
	
	/**
	 * ���캯��
	 */
	public ManyNodeTree()
	{
		root = new ManyTreeNode(new TreeNode("root"));
	}
	
	/**
	 * ����һ�Ŷ���������ڵ�Ϊroot
	 * 
	 * @param treeNodes ���ɶ�����Ľڵ㼯��
	 * @return ManyNodeTree
	 */
	public ManyNodeTree createTree(List<TreeNode> treeNodes)
	{
		if(treeNodes == null || treeNodes.size() < 0)
			return null;
		
		ManyNodeTree manyNodeTree =  new ManyNodeTree();
		
		//�����нڵ���ӵ��������
		for(TreeNode treeNode : treeNodes)
		{
			if(treeNode.getParentId().equals("root"))
			{
				//������һ���ڵ�
				manyNodeTree.getRoot().getChildList().add(new ManyTreeNode(treeNode));
			}
			else
			{
				addChild(manyNodeTree.getRoot(), treeNode);
			}
		}
		
		return manyNodeTree;
	}
	
	/**
	 * ��ָ��������ڵ�����ӽڵ�
	 * 
	 * @param manyTreeNode ������ڵ�
	 * @param child �ڵ�
	 */
	public void addChild(ManyTreeNode manyTreeNode, TreeNode child)
	{
		for(ManyTreeNode item : manyTreeNode.getChildList())
		{
			if(item.getData().getNodeId().equals(child.getParentId()))
			{
				//�ҵ���Ӧ�ĸ���
				item.getChildList().add(new ManyTreeNode(child));
				break;
			}
			else
			{
				if(item.getChildList() != null && item.getChildList().size() > 0)
				{
					addChild(item, child);
				}				
			}
		}
	}
	
	/**
	 * ��������� 
	 * 
	 * @param manyTreeNode ������ڵ�
	 * @return 
	 */
	public String iteratorTree(ManyTreeNode manyTreeNode)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n");
		
		if(manyTreeNode != null) 
		{	
			for (ManyTreeNode index : manyTreeNode.getChildList()) 
			{
				buffer.append(index.getData().getNodeId()+ ",");
				
				if (index.getChildList() != null && index.getChildList().size() > 0 ) 
				{	
					buffer.append(iteratorTree(index));
				}
			}
		}
		
		buffer.append("\n");
		
		return buffer.toString();
	}
	
	public ManyTreeNode getRoot() {
		return root;
	}

	public void setRoot(ManyTreeNode root) {
		this.root = root;
	}
	
	public static void main(String[] args)
	{
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
			/*treeNodes.add(new TreeNode("�û�����", "ϵͳȨ�޹���"));
			treeNodes.add(new TreeNode("ϵͳȨ�޹���", "root"));
			treeNodes.add(new TreeNode("��ɫ����", "ϵͳȨ�޹���"));
			treeNodes.add(new TreeNode("�����", "ϵͳȨ�޹���"));
			treeNodes.add(new TreeNode("�û��˵�����", "ϵͳȨ�޹���"));
			treeNodes.add(new TreeNode("��ɫ�˵�����", "ϵͳȨ�޹���"));
			treeNodes.add(new TreeNode("�û�Ȩ�޹���", "ϵͳȨ�޹���"));
			treeNodes.add(new TreeNode("վ����", "root"));
			treeNodes.add(new TreeNode("д��", "վ����"));
			treeNodes.add(new TreeNode("����", "վ����"));
				treeNodes.add(new TreeNode("�ݸ�", "վ����"));*/
			treeNodes.add(new TreeNode("1", "root"));
			treeNodes.add(new TreeNode("10", "1"));
			treeNodes.add(new TreeNode("11", "1"));
			treeNodes.add(new TreeNode("100", "10"));
			treeNodes.add(new TreeNode("101", "10"));
			treeNodes.add(new TreeNode("110", "11"));
			treeNodes.add(new TreeNode("111", "11"));
			treeNodes.add(new TreeNode("1000", "100"));
			treeNodes.add(new TreeNode("1001", "100"));
			
			ManyNodeTree tree = new ManyNodeTree();
			ManyNodeTree t = tree.createTree(treeNodes);
			System.out.println(t.getRoot().getChildList().size());
			System.out.println(tree.iteratorTree(t.getRoot()));
	}
	
}
