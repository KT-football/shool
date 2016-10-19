package com.ktfootball.www.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.ktfootball.www.dao.Bags;
import com.ktfootball.www.dao.Users;
import com.ktfootball.www.dao.Games;
import com.ktfootball.www.dao.SideAandB;
import com.ktfootball.www.dao.VcrPath;
import com.ktfootball.www.dao.UploadBigClassroomCourseRecord;
import com.ktfootball.www.dao.UploadBigClassroomCourseRecordBoolean;
import com.ktfootball.www.dao.RankingPower;
import com.ktfootball.www.dao.RankingLeagueScores;
import com.ktfootball.www.dao.RankingLeagueScores3v3;
import com.ktfootball.www.dao.RankingLeagueScores1v1;
import com.ktfootball.www.dao.UploadGymCourseRecord;
import com.ktfootball.www.dao.UserInfo;

import com.ktfootball.www.dao.BagsDao;
import com.ktfootball.www.dao.UsersDao;
import com.ktfootball.www.dao.GamesDao;
import com.ktfootball.www.dao.SideAandBDao;
import com.ktfootball.www.dao.VcrPathDao;
import com.ktfootball.www.dao.UploadBigClassroomCourseRecordDao;
import com.ktfootball.www.dao.UploadBigClassroomCourseRecordBooleanDao;
import com.ktfootball.www.dao.RankingPowerDao;
import com.ktfootball.www.dao.RankingLeagueScoresDao;
import com.ktfootball.www.dao.RankingLeagueScores3v3Dao;
import com.ktfootball.www.dao.RankingLeagueScores1v1Dao;
import com.ktfootball.www.dao.UploadGymCourseRecordDao;
import com.ktfootball.www.dao.UserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bagsDaoConfig;
    private final DaoConfig usersDaoConfig;
    private final DaoConfig gamesDaoConfig;
    private final DaoConfig sideAandBDaoConfig;
    private final DaoConfig vcrPathDaoConfig;
    private final DaoConfig uploadBigClassroomCourseRecordDaoConfig;
    private final DaoConfig uploadBigClassroomCourseRecordBooleanDaoConfig;
    private final DaoConfig rankingPowerDaoConfig;
    private final DaoConfig rankingLeagueScoresDaoConfig;
    private final DaoConfig rankingLeagueScores3v3DaoConfig;
    private final DaoConfig rankingLeagueScores1v1DaoConfig;
    private final DaoConfig uploadGymCourseRecordDaoConfig;
    private final DaoConfig userInfoDaoConfig;

    private final BagsDao bagsDao;
    private final UsersDao usersDao;
    private final GamesDao gamesDao;
    private final SideAandBDao sideAandBDao;
    private final VcrPathDao vcrPathDao;
    private final UploadBigClassroomCourseRecordDao uploadBigClassroomCourseRecordDao;
    private final UploadBigClassroomCourseRecordBooleanDao uploadBigClassroomCourseRecordBooleanDao;
    private final RankingPowerDao rankingPowerDao;
    private final RankingLeagueScoresDao rankingLeagueScoresDao;
    private final RankingLeagueScores3v3Dao rankingLeagueScores3v3Dao;
    private final RankingLeagueScores1v1Dao rankingLeagueScores1v1Dao;
    private final UploadGymCourseRecordDao uploadGymCourseRecordDao;
    private final UserInfoDao userInfoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bagsDaoConfig = daoConfigMap.get(BagsDao.class).clone();
        bagsDaoConfig.initIdentityScope(type);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        gamesDaoConfig = daoConfigMap.get(GamesDao.class).clone();
        gamesDaoConfig.initIdentityScope(type);

        sideAandBDaoConfig = daoConfigMap.get(SideAandBDao.class).clone();
        sideAandBDaoConfig.initIdentityScope(type);

        vcrPathDaoConfig = daoConfigMap.get(VcrPathDao.class).clone();
        vcrPathDaoConfig.initIdentityScope(type);

        uploadBigClassroomCourseRecordDaoConfig = daoConfigMap.get(UploadBigClassroomCourseRecordDao.class).clone();
        uploadBigClassroomCourseRecordDaoConfig.initIdentityScope(type);

        uploadBigClassroomCourseRecordBooleanDaoConfig = daoConfigMap.get(UploadBigClassroomCourseRecordBooleanDao.class).clone();
        uploadBigClassroomCourseRecordBooleanDaoConfig.initIdentityScope(type);

        rankingPowerDaoConfig = daoConfigMap.get(RankingPowerDao.class).clone();
        rankingPowerDaoConfig.initIdentityScope(type);

        rankingLeagueScoresDaoConfig = daoConfigMap.get(RankingLeagueScoresDao.class).clone();
        rankingLeagueScoresDaoConfig.initIdentityScope(type);

        rankingLeagueScores3v3DaoConfig = daoConfigMap.get(RankingLeagueScores3v3Dao.class).clone();
        rankingLeagueScores3v3DaoConfig.initIdentityScope(type);

        rankingLeagueScores1v1DaoConfig = daoConfigMap.get(RankingLeagueScores1v1Dao.class).clone();
        rankingLeagueScores1v1DaoConfig.initIdentityScope(type);

        uploadGymCourseRecordDaoConfig = daoConfigMap.get(UploadGymCourseRecordDao.class).clone();
        uploadGymCourseRecordDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        bagsDao = new BagsDao(bagsDaoConfig, this);
        usersDao = new UsersDao(usersDaoConfig, this);
        gamesDao = new GamesDao(gamesDaoConfig, this);
        sideAandBDao = new SideAandBDao(sideAandBDaoConfig, this);
        vcrPathDao = new VcrPathDao(vcrPathDaoConfig, this);
        uploadBigClassroomCourseRecordDao = new UploadBigClassroomCourseRecordDao(uploadBigClassroomCourseRecordDaoConfig, this);
        uploadBigClassroomCourseRecordBooleanDao = new UploadBigClassroomCourseRecordBooleanDao(uploadBigClassroomCourseRecordBooleanDaoConfig, this);
        rankingPowerDao = new RankingPowerDao(rankingPowerDaoConfig, this);
        rankingLeagueScoresDao = new RankingLeagueScoresDao(rankingLeagueScoresDaoConfig, this);
        rankingLeagueScores3v3Dao = new RankingLeagueScores3v3Dao(rankingLeagueScores3v3DaoConfig, this);
        rankingLeagueScores1v1Dao = new RankingLeagueScores1v1Dao(rankingLeagueScores1v1DaoConfig, this);
        uploadGymCourseRecordDao = new UploadGymCourseRecordDao(uploadGymCourseRecordDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(Bags.class, bagsDao);
        registerDao(Users.class, usersDao);
        registerDao(Games.class, gamesDao);
        registerDao(SideAandB.class, sideAandBDao);
        registerDao(VcrPath.class, vcrPathDao);
        registerDao(UploadBigClassroomCourseRecord.class, uploadBigClassroomCourseRecordDao);
        registerDao(UploadBigClassroomCourseRecordBoolean.class, uploadBigClassroomCourseRecordBooleanDao);
        registerDao(RankingPower.class, rankingPowerDao);
        registerDao(RankingLeagueScores.class, rankingLeagueScoresDao);
        registerDao(RankingLeagueScores3v3.class, rankingLeagueScores3v3Dao);
        registerDao(RankingLeagueScores1v1.class, rankingLeagueScores1v1Dao);
        registerDao(UploadGymCourseRecord.class, uploadGymCourseRecordDao);
        registerDao(UserInfo.class, userInfoDao);
    }
    
    public void clear() {
        bagsDaoConfig.getIdentityScope().clear();
        usersDaoConfig.getIdentityScope().clear();
        gamesDaoConfig.getIdentityScope().clear();
        sideAandBDaoConfig.getIdentityScope().clear();
        vcrPathDaoConfig.getIdentityScope().clear();
        uploadBigClassroomCourseRecordDaoConfig.getIdentityScope().clear();
        uploadBigClassroomCourseRecordBooleanDaoConfig.getIdentityScope().clear();
        rankingPowerDaoConfig.getIdentityScope().clear();
        rankingLeagueScoresDaoConfig.getIdentityScope().clear();
        rankingLeagueScores3v3DaoConfig.getIdentityScope().clear();
        rankingLeagueScores1v1DaoConfig.getIdentityScope().clear();
        uploadGymCourseRecordDaoConfig.getIdentityScope().clear();
        userInfoDaoConfig.getIdentityScope().clear();
    }

    public BagsDao getBagsDao() {
        return bagsDao;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public GamesDao getGamesDao() {
        return gamesDao;
    }

    public SideAandBDao getSideAandBDao() {
        return sideAandBDao;
    }

    public VcrPathDao getVcrPathDao() {
        return vcrPathDao;
    }

    public UploadBigClassroomCourseRecordDao getUploadBigClassroomCourseRecordDao() {
        return uploadBigClassroomCourseRecordDao;
    }

    public UploadBigClassroomCourseRecordBooleanDao getUploadBigClassroomCourseRecordBooleanDao() {
        return uploadBigClassroomCourseRecordBooleanDao;
    }

    public RankingPowerDao getRankingPowerDao() {
        return rankingPowerDao;
    }

    public RankingLeagueScoresDao getRankingLeagueScoresDao() {
        return rankingLeagueScoresDao;
    }

    public RankingLeagueScores3v3Dao getRankingLeagueScores3v3Dao() {
        return rankingLeagueScores3v3Dao;
    }

    public RankingLeagueScores1v1Dao getRankingLeagueScores1v1Dao() {
        return rankingLeagueScores1v1Dao;
    }

    public UploadGymCourseRecordDao getUploadGymCourseRecordDao() {
        return uploadGymCourseRecordDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}
