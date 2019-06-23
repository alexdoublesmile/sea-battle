package com.plohoy.seabattle.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.plohoy.seabattle.model.*;
import com.plohoy.seabattle.view.*;

public class SeaBattleController {
	SeaBattleModel theModel;	
	SeaBattleView theView;
//	Field theField;
	
	SeaBattleController(SeaBattleModel theModel, SeaBattleView theView) {
		
		this.theModel = theModel;		
		this.theView = theView;
		theModel.setGameOver(false);
		theModel.createShips(theView.getBattlefieldSize());
		theModel.createShots();
		theModel.createLabels();
		theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());

		theView.addShotListener(new shotListener());
	}
	
	class shotListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			
			
//			System.out.println("------------------------------------------------");
//			System.out.println("���������� �: " + x);	
//			System.out.println("���������� y: " + y);
//			System.out.println("------------------------------------------------");
//			int x, y;
//			do {
//				x = e.getX()/theView.getCELL_PLAYER_PX_SIZE();
//				y = e.getY()/theView.getCELL_PLAYER_PX_SIZE();
//			} while(theModel.getPlayerShots().hitSamePlace(x, y));
//			System.out.println("------------------------------------------------");
//			System.out.println("������ ������: " + theView.getCELL_PLAYER_PX_SIZE());	
//			System.out.println("��� ���������� y: (" + e.getX() + ", " + e.getY() + ")");
//			System.out.println("------------------------------------------------");
			int x = e.getX()/(theView.getCELL_PLAYER_PX_SIZE() * 9 / 10);
			int	y = e.getY()/(theView.getCELL_PLAYER_PX_SIZE() * 9 / 10);
//			System.out.println("------------------------------------------------");
//			System.out.println("������ ������: " + theView.getCELL_PLAYER_PX_SIZE());	
//			System.out.println("��� ���������� y: (" + e.getX() + ", " + e.getY() + ")");
//			System.out.println("------------------------------------------------");
			if(e.getButton() == theView.getMOUSE_BUTTON_LEFT() && !theModel.isGameOver()) {
//				System.out.println("������ ����� ���������!");
				while(theModel.getPlayerShots().hitSamePlace(x, y)) {
					theView.displayErrorMessage("�� ���� ��� ��������. ���� ����� ���������� � ������ �����..");
					return;
				}
				theModel.getPlayerShots().add(x, y, true);
//				System.out.println("------------------------------------------------");
//				System.out.println("��� ���������� �: " + x);	
//				System.out.println("��� ���������� y: " + y);
//				System.out.println("------------------------------------------------");
//				if(!theModel.getPlayerShots().hitSamePlace(x, y)) {
//					
//				} else {
//					theView.displayErrorMessage("�� ���� ��� ��������. ���� ����� ���������� � ������ �����..");
//					
//					return;
//				}

				
				if(theModel.getOpponentShips().checkHit(x, y)) {


					if(!theModel.getOpponentShips().checkSurvivors()) {
						theModel.setGameOver(true);
					}
					
				} else {
//					System.out.println("------------------------------------------------");
//					System.out.println("�� �����!");
//					System.out.println("��� ����� ��������");
					opponentShoots();
					theView.getOpponentBattleFieldPanel().repaint();
					theView.getPlayerBattleFieldPanel().repaint();
//					System.out.println("------------------------------------------------");
				}
//				System.out.println("--------------------- ��� ������ ���� ����������� ---------------------------");
				theView.getOpponentBattleFieldPanel().repaint();
//				theView.repaintAll();
			}
			if(e.getButton() == theView.getMOUSE_BUTTON_RIGHT()) {
				System.out.println("������ ������ ���������!");
			}
		}		
	}
	
	public void opponentShoots() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(theView.getBattlefieldSize());
			y = random.nextInt(theView.getBattlefieldSize());
			
			System.out.println("------------------------------------------------");
			System.out.println("���������� �: " + x);	
			System.out.println("���������� y: " + y);
			System.out.println("------------------------------------------------");
			

			
		} while(theModel.getOpponentShots().hitSamePlace(x, y));
		theModel.getOpponentShots().add(x, y, true);
		
		System.out.println("------------------------------------------------");	
		System.out.println("������ ���������:");
		int n = 1;
		for(Shot shot : theModel.getOpponentShots().getShots()) {
			System.out.println("���������� " + n + " ��������: (" + shot.getxCoord() + ", " + shot.getyCoord() + ").");
			n++;				
		}
		System.out.println("------------------------------------------------");	
		
		if (!theModel.getPlayerShips().checkHit(x, y)) {
			System.out.println("�������� ��������!!!");
			return;
		} else {
			System.out.println("�������� ����� ����� � �������!!!");
			if(!theModel.getPlayerShips().checkSurvivors()) {
				System.out.println("��� ��������...");
				theModel.setGameOver(true);
			} else {
				opponentShoots();
			}
		}
		
	}
}
