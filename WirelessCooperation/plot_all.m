results;

figure; plot(N,Strategy_TitForTatMobility_NoneAlpha_3_00,N,Strategy_TitForTatMobility_RandomWaypointAlpha_3_00);
legend('tft','tft\_rw')
xlabel('N');
ylabel('Energy');

figure; plot(N,Strategy_CoopMobility_NoneAlpha_3_00,N,Strategy_CoopMobility_RandomWaypointAlpha_3_00);
legend('coop','coop\_rw')
xlabel('N');
ylabel('Energy');

figure; plot(N,Strategy_WinStayLoseShiftMobility_NoneAlpha_3_00,N,Strategy_WinStayLoseShiftMobility_RandomWaypointAlpha_3_00);
legend('wsls','wsls\_rw')
xlabel('N');
ylabel('Energy');

figure; plot(N,Strategy_DefMobility_NoneAlpha_3_00,N,Strategy_DefMobility_RandomWaypointAlpha_3_00);
legend('def','def\_rw')
xlabel('N');
ylabel('Energy');